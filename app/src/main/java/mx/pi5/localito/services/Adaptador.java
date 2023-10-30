package mx.pi5.localito.services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.pi5.localito.R;
import mx.pi5.localito.layouts.StandNear;

public class Adaptador extends RecyclerView.Adapter<Adaptador.StandViewHolder> {
    List<StandNear.Stand> stands;

    public void setStands(List<StandNear.Stand> stands) {
        this.stands = stands;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stand_near, parent, false);
        return new StandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StandViewHolder holder, int position) {
        StandNear.Stand stand = stands.get(position);
        holder.titleTextView.setText(stand.getTitle());
        holder.descriptionTextView.setText(stand.getDescription());

        holder.loadImage(stand.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return stands != null ? stands.size() : 0;
    }

    static class StandViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageView imageView;

        StandViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.card_title);
            descriptionTextView = itemView.findViewById(R.id.card_description);
            imageView = itemView.findViewById(R.id.card_image);
        }

        void loadImage(String imageUrl) {
            new ImageLoader(imageView).execute(imageUrl);
        }
    }
}
