package mx.pi5.localito.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mx.pi5.localito.adapter.StandAdapter;
import mx.pi5.localito.databinding.FragmentStandsBinding;
import mx.pi5.localito.endpoints.GetStands;
import mx.pi5.localito.entity.Stand;
import mx.pi5.localito.service.Client;

public class Stands extends Fragment {

    protected FragmentStandsBinding binding;
    protected List<Stand> stands = new ArrayList<>();
    protected StandAdapter adapter;

    public Stands() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStandsBinding.inflate(inflater, container, false);
        Client client = Client.getInstance(requireContext());

        adapter = new StandAdapter(new ArrayList<>(), requireContext());
        binding.standsList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.standsList.setAdapter(adapter);

        binding.standsProgressBar.setVisibility(View.VISIBLE);

        loadData();

        return binding.getRoot();
    }

    private void loadData() {
        Client client = Client.getInstance(requireContext());
        client.getQueue().add(new GetStands(response -> {
            List<Stand> data = Arrays.asList(response);

            adapter.updateData(data);

            binding.standsProgressBar.setVisibility(View.GONE);
        }, error -> {
            Snackbar.make(binding.getRoot(), error.getMessage(), Snackbar.LENGTH_SHORT).show();

            binding.standsProgressBar.setVisibility(View.GONE);
        }));

        client.getQueue().start();
    }
}
