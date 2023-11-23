package mx.pi5.localito.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.pi5.localito.R;
import mx.pi5.localito.databinding.StandItemBinding;
import mx.pi5.localito.entity.Stand;

class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View view) {
        super(view);

    }
}

public class StandAdapter extends RecyclerView.Adapter<ViewHolder> {
    protected List<Stand> stands;

    public StandAdapter(List<Stand> stands) {
        this.stands = stands;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.stand_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return stands.size();
    }
}
