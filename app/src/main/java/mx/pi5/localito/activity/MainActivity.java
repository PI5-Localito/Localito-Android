package mx.pi5.localito.activity;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;

import mx.pi5.localito.AuthorizedActivity;
import mx.pi5.localito.R;
import mx.pi5.localito.databinding.ActivityMainBinding;

public class MainActivity extends AuthorizedActivity {
    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        b.navigation.setOnItemSelectedListener(this::navItemSelect);
    }

    private NavController getNavController() {
        NavHostFragment hostFragment =  b.container.getFragment();
        return hostFragment.getNavController();
    }

    private boolean navItemSelect(MenuItem item) {
        if (item.isChecked()) return false;
        NavController controller = getNavController();
        String route = "stands";
        if (item.getItemId() == R.id.categories) {
            route = "categories";
        } else if (item.getItemId() == R.id.orders) {
            route = "orders";
        } else if (item.getItemId() == R.id.more) {
            route = "more";
        }

        controller.navigate(route);

        return true;
    }
}
