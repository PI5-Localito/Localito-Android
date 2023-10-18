package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import mx.pi5.localito.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        b.searchBar.setOnClickListener(v -> {
            Toast.makeText(this, "A", Toast.LENGTH_SHORT).show();
        });
        // b.topbar.setOnMenuItemClickListener(this::navigationElement);
    }

    protected boolean navigationElement(MenuItem item) {
        if (item.getItemId() == R.id.app_search) {
            doSearch();
        }
        return true;
    }

    protected void doSearch() {
    }

}
