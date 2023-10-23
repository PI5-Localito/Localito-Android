package mx.pi5.localito.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mx.pi5.localito.R;
import mx.pi5.localito.databinding.AnonymLoginBinding;
import mx.pi5.localito.layouts.StandNear;

public class AnonymLogin extends AppCompatActivity {

    EditText user, contra;
    Button btn;
    String us="", ps="";
    AnonymLoginBinding b;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = AnonymLoginBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        user = (EditText) findViewById(R.id.etxmail);
        contra = (EditText) findViewById(R.id.etxpassword);
        btn = (Button) findViewById(R.id.btn_login);

        btn.setOnClickListener(view -> {
            us = user.getText().toString();
            ps = contra.getText().toString();
            if (us.isEmpty() || ps.isEmpty()) {
                Toast.makeText(AnonymLogin.this, "Debes llenar los campos primero", Toast.LENGTH_LONG).show();
            } else if (us.equals("usuario@mail.com") && ps.equals("contra123")) {
                i = new Intent(AnonymLogin.this, StandNear.class);
                startActivity(i);
            } else {
                Toast.makeText(AnonymLogin.this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
            }
        });

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
