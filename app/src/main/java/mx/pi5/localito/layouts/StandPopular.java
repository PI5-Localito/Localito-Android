package mx.pi5.localito.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import mx.pi5.localito.databinding.ActivityStandPopularBinding;

public class StandPopular extends AppCompatActivity {
    ActivityStandPopularBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityStandPopularBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
}
