package mx.pi5.localito.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import mx.pi5.localito.R;
import mx.pi5.localito.databinding.AnonymLoginBinding;

public class AnonymLogin extends AppCompatActivity {
    AnonymLoginBinding b;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = AnonymLoginBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
}
