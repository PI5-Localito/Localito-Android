package mx.pi5.localito.activity;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import mx.pi5.localito.AuthorizedActivity;
import mx.pi5.localito.R;
import mx.pi5.localito.databinding.ActivityMainBinding;
import mx.pi5.localito.service.Client;

public class MainActivity extends AuthorizedActivity {

    private ActivityMainBinding b;
    private SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG_19", "Uso de Activity/MainActivity.java ...");
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        b.navigation.setOnItemSelectedListener(this::navItemSelect);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (!preferences.getBoolean("isStandsFragmentShown", false)) {
            showStandsFragment();
            preferences.edit().putBoolean("isStandsFragmentShown", true).apply();
        }
    }

    private void showStandsFragment() {
        NavController controller = getNavController();
        controller.navigate("stands");
        Client client = Client.getInstance(this);
        client.getQueue().stop();
    }

    private NavController getNavController() {
        NavHostFragment hostFragment = b.container.getFragment();
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
