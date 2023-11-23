package mx.pi5.localito.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mx.pi5.localito.R;
import mx.pi5.localito.adapter.CategoriesAdapter;
import mx.pi5.localito.databinding.FragmentCategoriesBinding;
import mx.pi5.localito.entity.Category;


public class Categories extends Fragment {
    FragmentCategoriesBinding binding;
    ArrayList<Category> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false);
        list.add(new Category("Comida", R.mipmap.comida));
        list.add(new Category("Herramientas", R.mipmap.herramientas));
        list.add(new Category("Moda", R.mipmap.moda));
        list.add(new Category("Servicios", R.mipmap.servicios));
        list.add(new Category("Mascotas", R.mipmap.mascotas));
        binding.categoriesList.setAdapter(new CategoriesAdapter(list));
        return binding.getRoot();
    }
}
