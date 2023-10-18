package mx.pi5.localito.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import mx.pi5.localito.databinding.ActivityStandNearBinding;

public class StandNear extends AppCompatActivity {
    ActivityStandNearBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityStandNearBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
}
