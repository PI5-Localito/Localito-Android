package mx.pi5.localito.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.pi5.localito.databinding.MessageItemBinding;
import mx.pi5.localito.databinding.ProductItemBinding;
import mx.pi5.localito.entity.Message;
import mx.pi5.localito.entity.Product;
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
            b.date.setText(message.message_timestamp);
            b.getRoot().setVisibility(View.VISIBLE);

        }
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
