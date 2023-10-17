package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import mx.pi5.localito.databinding.ActivitySellerProfileBinding;
public class SellerProfile extends AppCompatActivity {

    ActivitySellerProfileBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivitySellerProfileBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
}
