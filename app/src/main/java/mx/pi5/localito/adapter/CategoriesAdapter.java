package mx.pi5.localito.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.pi5.localito.R;
import mx.pi5.localito.activity.CategoryStands;
import mx.pi5.localito.databinding.ActivityStandsCategoryBinding;
import mx.pi5.localito.databinding.CategoryItemBinding;
import mx.pi5.localito.entity.Category;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        CategoryItemBinding binding;
        Context ctx;
        protected String category = null;

        public ViewHolder(CategoryItemBinding binding, Context ctx) {
            super(binding.getRoot());
            this.binding = binding;
            this.ctx = ctx;
            binding.getRoot().setVisibility(View.INVISIBLE);
        }

        protected void onClick(View view) {
            if (category == null) return;
            Intent intent = new Intent(ctx, CategoryStands.class);
            String categ = null;

            intent.putExtra("category", category);
            ctx.startActivity(intent);
        }

        public void bind(Category category) {
            binding.title.setText(category.name);
            binding.categImage.setImageResource(category.imageUri);
            binding.getRoot().setVisibility(View.VISIBLE);
            binding.getRoot().setOnClickListener(this::onClick);
            this.category = category.name;
        }
    }
    protected List<Category> categories;

    public CategoriesAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();
        CategoryItemBinding binding = CategoryItemBinding.inflate(
            LayoutInflater.from(ctx),
            parent, false
        );

        return new ViewHolder(binding, ctx);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category cat = categories.get(position);
        holder.bind(cat);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
