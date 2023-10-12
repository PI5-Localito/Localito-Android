package mx.pi5.localito.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import mx.pi5.localito.R;
import mx.pi5.localito.databinding.ActivityMainBinding;

public class AnonymLogin extends AppCompatActivity {
    ActivityMainBinding b;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
}
