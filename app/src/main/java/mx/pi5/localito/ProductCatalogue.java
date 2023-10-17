package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import mx.pi5.localito.databinding.ActivityProductCatalogueBinding;

public class ProductCatalogue extends AppCompatActivity {
    ActivityProductCatalogueBinding b;
    String[] products = {"Coca", "Pepsi", "Cagada de perro", "Piedad de Dios"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityProductCatalogueBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.rvProducts.setLayoutManager(new LinearLayoutManager(this));
        ProductAdapter adapter = new ProductAdapter(products);
        b.rvProducts.setAdapter(adapter);
    }
}
