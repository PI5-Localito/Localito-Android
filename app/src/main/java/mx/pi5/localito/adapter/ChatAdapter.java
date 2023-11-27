package mx.pi5.localito.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.pi5.localito.databinding.MessageItemBinding;
import mx.pi5.localito.databinding.ProductItemBinding;
import mx.pi5.localito.entity.Message;
import mx.pi5.localito.entity.Product;
import mx.pi5.localito.service.Auth;
import mx.pi5.localito.service.Client;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private final Context ctx;

    class ViewHolder extends RecyclerView.ViewHolder{
        Message message;
        MessageItemBinding b;
        Context ctx;

        public ViewHolder(MessageItemBinding b, Context ctx){
            super(b.getRoot());
            this.b = b;
            this.ctx = ctx;
            b.getRoot().setVisibility(View.INVISIBLE);
        }

        public void bind(Message message){
            this.message = message;
            Client client = Client.getInstance(null);
            b.body.setText(message.body);
            String created_at = dateFormat(message.message_timestamp);
            b.date.setText(created_at);
            b.getRoot().setVisibility(View.VISIBLE);

            if(message.user_from == Auth.getInstance(null).getData().id){
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) b.container.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_END);
            }else {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) b.container.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_START);
            }
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
    protected List<Message> messages;

    public ChatAdapter(List<Message> messages, Context ctx) {
        this.messages = messages;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageItemBinding b = MessageItemBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent, false
        );
        return new ChatAdapter.ViewHolder(b, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Client.getInstance(null).getQueue().start();
    }
}


//[{"id":15,"buyer_id":20,"seller_id":4,"stand_id":3,"date":"2023-11-27 03:11:26","state":"PENDING"},{"id":16,"buyer_id":20,"seller_id":4,"stand_id":3,"date":"2023-11-27 03:11:47","state":"PENDING"},{"id":18,"buyer_id":20,"seller_id":5,"stand_id":4,"date":"2023-11-27 04:11:13","state":"PENDING"},{"id":19,"buyer_id":20,"seller_id":6,"stand_id":6,"date":"2023-11-27 04:11:18","state":"PENDING"},{"id":20,"buyer_id":20,"seller_id":7,"stand_id":7,"date":"2023-11-27 04:11:31","state":"PENDING"},{"id":21,"buyer_id":20,"seller_id":5,"stand_id":4,"date":"2023-11-27 04:11:33","state":"PENDING"},{"id":24,"buyer_id":20,"seller_id":9,"stand_id":8,"date":"2023-11-27 05:11:30","state":"FINISHED"},{"id":25,"buyer_id":20,"seller_id":9,"stand_id":8,"date":"2023-11-27 05:11:32","state":"REJECTED"}]
