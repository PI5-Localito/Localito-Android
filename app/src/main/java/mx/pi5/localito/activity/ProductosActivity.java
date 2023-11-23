package mx.pi5.localito.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mx.pi5.localito.AuthorizedActivity;
import mx.pi5.localito.adapter.ProductAdapter;
import mx.pi5.localito.databinding.ActivityStandProductsBinding;
import mx.pi5.localito.endpoints.GetProducts;
import mx.pi5.localito.endpoints.GetStand;
import mx.pi5.localito.entity.Product;
import mx.pi5.localito.service.Client;

public class ProductosActivity extends AuthorizedActivity {
    protected ActivityStandProductsBinding binding;
    protected List<Product> stands = new ArrayList<>();
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStandProductsBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Client client = Client.getInstance(this);

        client.getQueue().add(new GetStand(id, response -> {
            binding.info.setText(response.info);
            binding.title.setText(response.stand_name);
        }, error -> Snackbar.make(binding.getRoot(), error.getMessage(), Snackbar.LENGTH_SHORT).show() ));
        client.getQueue().add(new GetProducts(id, response -> {
            List<Product> products = Arrays.asList(response);
            binding.productList.setAdapter(new ProductAdapter(products));
        }, error -> Snackbar.make(binding.getRoot(), error.getMessage(), Snackbar.LENGTH_SHORT).show() ));
        client.getQueue().start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Client.getInstance(this).getQueue().stop();
    }
}
