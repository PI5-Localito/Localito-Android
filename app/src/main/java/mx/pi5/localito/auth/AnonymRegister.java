package mx.pi5.localito.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import mx.pi5.localito.R;
import mx.pi5.localito.databinding.ActivityMainBinding;
import  mx.pi5.localito.auth.anonym_register;

public class AnonymRegister extends AppCompatActivity {
    ActivityMainBinding b;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
}
