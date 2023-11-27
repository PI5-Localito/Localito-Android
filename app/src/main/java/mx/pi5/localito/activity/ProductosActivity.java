package mx.pi5.localito.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
            //binding.info.setText(response.info);
            binding.title.setText(response.stand_name);
            handleDescriptionText(response.info);
        }, error -> Snackbar.make(binding.getRoot(), error.getMessage(), Snackbar.LENGTH_SHORT).show() ));
        client.getQueue().add(new GetProducts(id, response -> {
            List<Product> products = Arrays.asList(response);
            binding.productList.setAdapter(new ProductAdapter(products));
        }, error -> Snackbar.make(binding.getRoot(), error.getMessage(), Snackbar.LENGTH_SHORT).show() ));
        client.getQueue().start();
    }

    // Método para manejar la lógica de "Ver más" para la descripción
    private void handleDescriptionText(String description) {
        if (description.length() > 80) {
            binding.info.setText(description.substring(0, 80) + "...");
            binding.seeMoreButton.setVisibility(View.VISIBLE);
            binding.seeMoreButton.setText("Ver más");

            binding.seeMoreButton.setOnClickListener(v -> {
                if (binding.info.getMaxLines() > 3) {
                    binding.info.setMaxLines(3);
                    binding.info.setText(description.substring(0, 80) + "...");
                    binding.seeMoreButton.setText("Ver más");
                } else {
                    binding.info.setMaxLines(Integer.MAX_VALUE);
                    binding.info.setText(description);
                    binding.seeMoreButton.setText("Ver menos");
                }
            });

        } else {
            binding.info.setText(description);
            binding.seeMoreButton.setVisibility(View.GONE); // Si no hay más de 3 líneas, oculta el botón
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Client.getInstance(this).getQueue().stop();
    }
}
