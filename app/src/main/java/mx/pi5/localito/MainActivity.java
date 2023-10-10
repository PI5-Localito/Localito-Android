package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;

import mx.pi5.localito.databinding.ActivityMainBinding;
import mx.pi5.localito.layouts.HeaderView;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.app.Application;
import com.google.android.material.color.DynamicColors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding b;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.btnRegister.setOnClickListener(view -> {startView("anonym_register");});
        b.btnLogin.setOnClickListener(view -> {startView("anonym_login");});
    }

    public void startView(String nombre){
        try {
            i = new Intent(MainActivity.this, Class.forName(nombre));
            startActivity(i);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
=======
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent i;
    Button to_login, to_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anonym_register);

>>>>>>> miguel
    }
}
