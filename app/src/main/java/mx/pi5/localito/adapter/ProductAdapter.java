package mx.pi5.localito.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.List;

import mx.pi5.localito.ApiRequest;
import mx.pi5.localito.databinding.ProductItemBinding;
import mx.pi5.localito.endpoints.Orders;
import mx.pi5.localito.entity.Order;
import mx.pi5.localito.entity.Product;
import mx.pi5.localito.service.Client;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder {
        Product product;
        ProductItemBinding binding;
        Context ctx;

        public ViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setVisibility(View.INVISIBLE);
            ctx = binding.getRoot().getContext();
        }

        protected void onClick(View view) {
            assert product != null;
        }

        public void bind(Product product) {
            this.product = product;
            binding.getRoot().setVisibility(View.VISIBLE);
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(binding.image, 0, 0);

            Client.getInstance(null).getImageLoader()
                .get(ApiRequest.BASE + product.image, listener);

            binding.title.setText(product.name);
            binding.info.setText(product.info);
            binding.price.setText("$" + product.price);

            binding.btnOrder.setOnClickListener(view -> {
                Client client = Client.getInstance(ctx);

                Order order = new Order();

                int buyer =
                int stand_id = product.stand_id;
                order.buyer_id = 1;
                order.seller_id = 20;
                order.stand_id = product.stand_id;

                client.getQueue().add(new Orders(stand_id, response -> {
                    Toast.makeText(ctx, "Orden Creada", Toast.LENGTH_SHORT).show();
                }, error -> Snackbar.make( binding.getRoot(), error.getMessage(), Snackbar.LENGTH_SHORT).show()) {
                    public String getBodyContentType() {
                        return "application/json";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        Gson gson = new Gson();
                        return gson.toJson(order).getBytes();
                    }
                });
            });
        }
    }

    protected List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Client.getInstance(null).getQueue().start();
    }
}
