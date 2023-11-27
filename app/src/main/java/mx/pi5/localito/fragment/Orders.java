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

import mx.pi5.localito.adapter.OrderAdapter;
import mx.pi5.localito.databinding.FragmentOrdersBinding;
import mx.pi5.localito.endpoints.GetOrders;
import mx.pi5.localito.entity.Order;
import mx.pi5.localito.service.Client;

public class Orders extends Fragment {
    protected FragmentOrdersBinding binding;
    protected List<Order> Orders = new ArrayList<>();

    public Orders() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        Client client = Client.getInstance(this.getContext());

        client.getQueue().add(new GetOrders(response -> {
            List<Order> data = Arrays.asList(response);
            Orders.clear();
            Orders.addAll(data);
            binding.ordersList.setAdapter(new OrderAdapter(Orders));
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
