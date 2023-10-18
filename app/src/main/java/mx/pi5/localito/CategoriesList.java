package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import mx.pi5.localito.databinding.ActivityCategoriesListBinding;

public class CategoriesList extends AppCompatActivity {
    ActivityCategoriesListBinding b;
    String[] stands = {"Comida", "Carros", "Ropa", "Pito"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityCategoriesListBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.rvCategorias.setLayoutManager(new GridLayoutManager(this, 2));
        ProductAdapter adapter = new ProductAdapter(stands);
        b.rvCategorias.setAdapter(adapter);
    }
}
