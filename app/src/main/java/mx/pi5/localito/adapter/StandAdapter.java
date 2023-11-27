package mx.pi5.localito.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import mx.pi5.localito.ApiRequest;
import mx.pi5.localito.R;
import mx.pi5.localito.activity.ProductosActivity;
import mx.pi5.localito.databinding.StandItemBinding;
import mx.pi5.localito.entity.Stand;
import mx.pi5.localito.service.Client;


public class StandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    protected List<Stand> stands;
    private boolean isLoading = false;
    private Context context;

    public StandAdapter(List<Stand> stands, Context context) {
        this.stands = stands;
        this.context = context;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyDataSetChanged();
    }

    public void updateData(List<Stand> newData) {
        stands.clear();
        stands.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            StandItemBinding binding = StandItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false
            );
            return new ViewHolder(binding);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_spinner, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            Stand stand = stands.get(position);
            ((ViewHolder) holder).bind(stand);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return stands.size() + (isLoading ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < stands.size() && stands.get(position) != null) {
            return VIEW_TYPE_ITEM;
        } else {
            return VIEW_TYPE_LOADING;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
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

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    //java.io.InterruptedIOException: thread interrupted
}
