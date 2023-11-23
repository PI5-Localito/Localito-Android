package mx.pi5.localito.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.pi5.localito.R;
import mx.pi5.localito.databinding.CategoryItemBinding;
import mx.pi5.localito.entity.Category;
import mx.pi5.localito.fragment.Categories;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        CategoryItemBinding binding;

        public ViewHolder(CategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setVisibility(View.INVISIBLE);
        }

        public void bind(Category category) {
            binding.title.setText(category.name);
            binding.getRoot().setVisibility(View.VISIBLE);
        }
    }
    protected List<Category> categories;

    public CategoriesAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemBinding binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
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
