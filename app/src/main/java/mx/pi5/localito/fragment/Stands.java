package mx.pi5.localito.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

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
        Client.getInstance(this.getContext()).getQueue().add(new GetStands() {
            @Override
            protected void deliverResponse(Stand[] response) {
                ArrayList<Stand> stands = Array
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStandsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
