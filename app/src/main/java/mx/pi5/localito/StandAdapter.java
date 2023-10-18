package mx.pi5.localito;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StandAdapter extends RecyclerView.Adapter<StandAdapter.StandViewHolder> {
    private String[] stands;

    public StandAdapter(String[] stands) {
        this.stands = stands;
    }

    @NonNull
    @Override
    public StandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stand_list, parent, false);
        return new StandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StandViewHolder holder, int position) {
        holder.bind(stands[position]);
    }

    @Override
    public int getItemCount() {
        return stands.length;
    }

    public class StandViewHolder extends RecyclerView.ViewHolder {
        private TextView txtStand;

        public StandViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStand = itemView.findViewById(R.id.txtStand);
        }

        public void bind(String product) {
            txtStand.setText(product);
        }
    }
}
