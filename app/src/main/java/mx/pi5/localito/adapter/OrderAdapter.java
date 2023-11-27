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
import mx.pi5.localito.R;
import mx.pi5.localito.activity.ChatActivity;
import mx.pi5.localito.activity.ProductosActivity;
import mx.pi5.localito.databinding.OrderItemBinding;
import mx.pi5.localito.entity.Order;
import mx.pi5.localito.service.Client;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            Intent intent = new Intent(ctx, ChatActivity.class);
            intent.putExtra("orderId", order.id);
            intent.putExtra("buyerId", order.buyer_id);
            ctx.startActivity(intent);
        }

        public void bind(Order order) {
            this.order = order;
            Integer Id = order.id;
            String estado =  order.state;
            String fecha = dateFormat(order.date);
            if(estado.contains("PENDING")){
                estado = "Pendiente de respuesta";
            } else if (estado.contains("ACCEPTED")) {
                estado = "Pedido CONFIRMADO por el vendedor";
                binding.orderIconState.setImageResource(R.drawable.baseline_confirm_24);
            } else if (estado.contains("REJECTED")) {
                estado = "Pedido RECHAZADO por el vendedor";
                binding.orderIconState.setImageResource(R.drawable.baseline_rejected_24);
            } else if (estado.contains("FINISHED")) {
                estado = "Pedido FINALIZADO";
                binding.orderIconState.setImageResource(R.drawable.baseline_finished_24);
            }
            binding.orderId.setText("Tu pedido es el núm "+ Id);
            binding.orderState.setText(estado);
            binding.orderCreated.setText(fecha);

            binding.getRoot().setOnClickListener(this::onClick);
            binding.getRoot().setVisibility(View.VISIBLE);
        }
    }

    public String dateFormat(String fechaOriginal){
        SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date fechaDate = null;
        try {
            fechaDate = formatoOriginal.parse(fechaOriginal);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat formatoDeseado = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formatoDeseado.format(fechaDate);

        return fechaFormateada;
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

//[
//    {
//    buyer_id: 20,
//    seller_id: 4,
//    stand_id: 3,
//    date: "2023-11-27 03:11:26",
//    state: "PENDING",
//    id: 15,
//    },
//];
