package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import mx.pi5.localito.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {
    ActivityAuthBinding b;
    String usu, pass;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
}
