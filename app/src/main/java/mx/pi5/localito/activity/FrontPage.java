package mx.pi5.localito.activity;

import androidx.appcompat.app.AppCompatActivity;

import mx.pi5.localito.databinding.FrontPageBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FrontPage extends AppCompatActivity {

    FrontPageBinding b;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = FrontPageBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.btnRegister.setOnClickListener(view -> startView(AnonymRegister.class));
        b.btnLogin.setOnClickListener(view -> startView(LoginActivity.class));
    }

    private void startView(Class<?> clazz) {
        Intent intent = new Intent(FrontPage.this, clazz);
        startActivity(intent);
    }
}
