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

        b.btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usu = b.txtUsu.getText().toString();
                pass = b.txtPass.getText().toString();

                if(usu.isEmpty() || pass.isEmpty()){
                    //Toast.makeText(AuthActivity.this, "Llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    i = new Intent(AuthActivity.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
