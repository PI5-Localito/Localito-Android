package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;
import mx.pi5.localito.layouts.HeaderView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent i;
    Button to_login, to_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anonym_login);

    }
}
