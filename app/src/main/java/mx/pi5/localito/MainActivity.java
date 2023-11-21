package mx.pi5.localito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import mx.pi5.localito.Auth.AuthManager;
import mx.pi5.localito.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding b;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        if (savedInstanceState == null) {
            navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            if (navController == null) {
                Log.e("MainActivity", "NavController is null");
                return;
            }
            // Configurar el BottomNavigationView
            NavigationUI.setupWithNavController(b.navView, navController);

            // oyente para manejar eventos de navegación
            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                //getSupportActionBar().setTitle(destination.getLabel());
            });
        }

        b.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    navController.navigate(R.id.stands);
                    return true;
                } else if (itemId == R.id.categories) {
                    navController.navigate(R.id.categories);
                    return true;
                } else if (itemId == R.id.orders) {
                    navController.navigate(R.id.orders);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void startAuthentication(){
        AuthManager authManager = new AuthManager(
            this,
            "client-id",
            "cliente-secreto",
            "com.example://oauth-callback"
        );
        // Inicia el flujo de autenticacion
        authManager.authenticate();
    }
}
