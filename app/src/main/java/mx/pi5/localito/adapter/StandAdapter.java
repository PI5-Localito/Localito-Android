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
import mx.pi5.localito.databinding.StandItemBinding;
import mx.pi5.localito.entity.Stand;
import mx.pi5.localito.service.Client;


public class StandAdapter extends RecyclerView.Adapter<StandAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder {
        Stand stand;
        StandItemBinding binding;
        Context ctx;

        public ViewHolder(StandItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setVisibility(View.INVISIBLE);
            ctx = binding.getRoot().getContext();
        }

        protected void onClick(View view) {
            assert stand != null;
            Intent intent = new Intent(ctx, ProductosActivity.class);
            intent.putExtra("id", stand.id);
            ctx.startActivity(intent);
        }

        public void bind(Stand stand) {
            this.stand = stand;
            binding.title.setText(stand.stand_name);
            binding.info.setText(stand.info);
            binding.getRoot().setOnClickListener(this::onClick);
            binding.getRoot().setVisibility(View.VISIBLE);

            Client client = Client.getInstance(null);
            ImageLoader loader = client.getImageLoader();
            ImageLoader.ImageListener listener = new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    binding.image.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(binding.getRoot().getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            };
            loader.get(ApiRequest.BASE + "/" + stand.image, listener);
        }
    }

    protected List<Stand> stands;

    public StandAdapter(List<Stand> stands) {
        this.stands = stands;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StandItemBinding binding = StandItemBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stand stand = stands.get(position);
        holder.bind(stand);
    }

    @Override
    public int getItemCount() {
        return stands.size();
    }
}
