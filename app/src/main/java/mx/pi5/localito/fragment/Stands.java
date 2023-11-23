package mx.pi5.localito.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import mx.pi5.localito.adapter.StandAdapter;
import mx.pi5.localito.databinding.FragmentStandsBinding;
import mx.pi5.localito.endpoints.GetStands;
import mx.pi5.localito.entity.Stand;
import mx.pi5.localito.service.Client;

public class Stands extends Fragment {
    protected FragmentStandsBinding binding;

    public Stands() { }

    public static Stands newInstance() {
        return new Stands();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStandsBinding.inflate(inflater, container, false);
        Client.getInstance(this.getContext()).getQueue().add(new GetStands() {
            @Override
            protected void deliverResponse(Stand[] response) {
                List<Stand> stands = Arrays.asList(response);
                binding.standsList.setAdapter(new StandAdapter(stands));
            }
        });
        Client.getInstance(null).getQueue().start();

        return binding.getRoot();
    }
}
