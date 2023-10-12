package mx.pi5.localito.layouts;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.search.SearchView;

import mx.pi5.localito.R;

public class HeaderView extends AppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_view);

        // Encuentra la referencia del SearchView
        searchView = findViewById(R.id.search_bar);

        // Configura un listener para manejar cambios en el texto de búsqueda
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Este método se llama cuando se presiona Enter o se envía la búsqueda
//                // Aquí se maneja la lógica de búsqueda
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Este método se llama cuando el texto de búsqueda cambia
//                // Realiza acciones mientras el usuario escribe
//                return false;
//            }
//        });
    }
}
