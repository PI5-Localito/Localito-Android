package mx.pi5.localito.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

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
    }
}
