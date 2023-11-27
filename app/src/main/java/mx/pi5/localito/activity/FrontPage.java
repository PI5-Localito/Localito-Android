package mx.pi5.localito.activity;

import androidx.appcompat.app.AppCompatActivity;

import mx.pi5.localito.databinding.FrontPageBinding;

import android.content.Intent;
import android.os.Bundle;

public class FrontPage extends AppCompatActivity {

    FrontPageBinding b;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = FrontPageBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.btnRegister.setOnClickListener(view -> {startView("AnonymRegister");});
        b.btnLogin.setOnClickListener(view -> {startView("AnonymLogin");});
    }

    public void startView(String nombre){
        try {
            String packageName = getPackageName();
            String className = packageName + ".auth." + nombre;
            i = new Intent(FrontPage.this, Class.forName(className));
            startActivity(i);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
