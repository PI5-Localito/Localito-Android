package mx.pi5.localito.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import mx.pi5.localito.R;
import mx.pi5.localito.databinding.AnonymRegisterBinding;

public class AnonymRegister extends AppCompatActivity {
    AnonymRegisterBinding b;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = AnonymRegisterBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setSupportActionBar(b.btnTopAppBack);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Realiza el regreso a la actividad anterior
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
