package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import mx.pi5.localito.adapters.ProductAdapter;
import mx.pi5.localito.databinding.ActivityProductCatalogueBinding;
import mx.pi5.localito.databinding.ActivityStandsListBinding;

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

    public static class StandsList extends AppCompatActivity {
        ActivityStandsListBinding b;
        String[] stands = {"Tortas el pepe", "Carniceria la Grasa", "Klub de Kanto y Komedia", "Pinturas weon"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            b = ActivityStandsListBinding.inflate(getLayoutInflater());
            setContentView(b.getRoot());

            b.rvStands.setLayoutManager(new LinearLayoutManager(this));
            ProductAdapter adapter = new ProductAdapter(stands);
            b.rvStands.setAdapter(adapter);
        }
    }
}
