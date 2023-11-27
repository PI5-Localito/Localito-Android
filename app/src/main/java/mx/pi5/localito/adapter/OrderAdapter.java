package mx.pi5.localito.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import mx.pi5.localito.ApiRequest;
import mx.pi5.localito.activity.ProductosActivity;
import mx.pi5.localito.databinding.OrderItemBinding;
import mx.pi5.localito.entity.Order;
import mx.pi5.localito.service.Client;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder {
        Order order;
        OrderItemBinding binding;
        Context ctx;

        public ViewHolder(OrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setVisibility(View.INVISIBLE);
            ctx = binding.getRoot().getContext();
        }

        protected void onClick(View view) {
            assert order != null;
            Intent intent = new Intent(ctx, ProductosActivity.class);
            intent.putExtra("id", order.id);
            ctx.startActivity(intent);
        }

        public void bind(Order order) {
            this.order = order;
            binding.orderId.setText(order.id + "");
            binding.getRoot().setOnClickListener(this::onClick);
            binding.getRoot().setVisibility(View.VISIBLE);
        }
    }

    protected List<Order> orders;

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderItemBinding binding = OrderItemBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
