package mx.pi5.localito.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mx.pi5.localito.AuthorizedActivity;
import mx.pi5.localito.adapter.StandAdapter;
import mx.pi5.localito.databinding.ActivityStandsCategoryBinding;
import mx.pi5.localito.endpoints.GetStandsByCategory;
import mx.pi5.localito.entity.Stand;
import mx.pi5.localito.service.Client;

public class CategoryStands extends AuthorizedActivity {
    protected ActivityStandsCategoryBinding binding;
    protected List<Stand> stands = new ArrayList<>();
    String category;

    public CategoryStands() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStandsCategoryBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Client client = Client.getInstance(this);

        client.getQueue().add(new GetStandsByCategory(category, response -> {
                List<Stand> data = Arrays.asList(response);
                binding.standsList.setAdapter(new StandAdapter(data));
            }, error -> Snackbar.make(
                    binding.getRoot(),
                    error.getMessage(),
                    Snackbar.LENGTH_SHORT)
                .show() )
        );
        client.getQueue().start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Client.getInstance(this).getQueue().stop();
    }
}
