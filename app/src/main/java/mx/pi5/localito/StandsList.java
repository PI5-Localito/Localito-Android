package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import mx.pi5.localito.databinding.ActivityStandsListBinding;

public class StandsList extends AppCompatActivity {
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
