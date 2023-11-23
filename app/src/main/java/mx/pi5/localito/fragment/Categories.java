package mx.pi5.localito.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;

import mx.pi5.localito.R;
import mx.pi5.localito.adapter.CategoriesAdapter;
import mx.pi5.localito.databinding.FragmentCategoriesBinding;

public class Categories extends Fragment {
    FragmentCategoriesBinding binding;
    ArrayList<String> list = new ArrayList<>();

    public Categories() { }

    public static Categories newInstance() {
        return new Categories();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false);
        list.add("N");
        binding.categoriesList.setAdapter(new CategoriesAdapter(list));
        return binding.getRoot();
    }
}
