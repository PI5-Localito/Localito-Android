package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mx.pi5.localito.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

    }
}
