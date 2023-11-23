package mx.pi5.localito.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

    public Stands() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStandsBinding.inflate(inflater, container, false);
        stands.add(new Stand());
        stands.add(new Stand());
        stands.add(new Stand());
        Client client = Client.getInstance(this.getContext());

        client.getQueue().add(new GetStands(response -> {
            List<Stand> data = Arrays.asList(response);
            stands.clear();
            stands.addAll(data);
            binding.standsList.setAdapter(new StandAdapter(stands));
        }, error -> Snackbar.make(
            binding.getRoot(),
            error.getMessage(),
            Snackbar.LENGTH_SHORT)
            .show() )
        );
        client.getQueue().start();
        return binding.getRoot();
    }
}
