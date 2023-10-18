package mx.pi5.localito.layouts;

import androidx.appcompat.app.AppCompatActivity;
import mx.pi5.localito.databinding.ActivityCategoriesViewBinding;
import android.os.Bundle;

import mx.pi5.localito.R;

public class categoriesView extends AppCompatActivity {
    ActivityCategoriesViewBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityCategoriesViewBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
}
