package mx.pi5.localito.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import mx.pi5.localito.ApiRequest;
import mx.pi5.localito.databinding.ProductItemBinding;
import mx.pi5.localito.entity.Order;
import mx.pi5.localito.entity.Product;
import mx.pi5.localito.service.Client;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder {
        Order order;
        ProductItemBinding binding;
        Context ctx;

        public ViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setVisibility(View.INVISIBLE);
            ctx = binding.getRoot().getContext();
        }

        protected void onClick(View view) {
        }

        public void bind(Product product) {
            binding.getRoot().setVisibility(View.VISIBLE);
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(binding.image, 0, 0);

            Client.getInstance(null).getImageLoader()
                .get(ApiRequest.BASE + product.image, listener);

        }
    }


    public OrderAdapter(List<Order> products) {
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Client.getInstance(null).getQueue().start();
    }
}
