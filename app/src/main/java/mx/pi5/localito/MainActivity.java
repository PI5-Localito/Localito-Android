package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;

import mx.pi5.localito.databinding.ActivityMainBinding;
import mx.pi5.localito.layouts.HeaderView;

import android.content.Intent;
import android.os.Bundle;
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

        b.btnRegister.setOnClickListener(view -> {startView("AnonymRegister");});
        b.btnLogin.setOnClickListener(view -> {startView("AnonymLogin");});
    }

    public void startView(String nombre){
        try {
            String packageName = getPackageName();
            String className = packageName + ".auth." + nombre; //Construye el nombre completo de la clase
            i = new Intent(MainActivity.this, Class.forName(className));
            startActivity(i);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
