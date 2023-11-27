package mx.pi5.localito.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import java.util.logging.Logger;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import mx.pi5.localito.databinding.ActivityMainBinding;
import mx.pi5.localito.endpoint.CurrentLocation;
import mx.pi5.localito.entity.City;
import mx.pi5.localito.service.ApiClient;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Inject
    ApiClient client;
    @Inject
    WifiManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getLocation();
    }

    public Task<Location> getLocation() {
        FusedLocationProviderClient locationProviderClient;
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 102);
            return null;
        }
        return locationProviderClient.getLastLocation()
            .addOnSuccessListener(this::onLocationProvided)
            .addOnFailureListener(f -> Logger.getLogger("SI").info(f.getMessage()));
    }

    public void onLocationProvided(Location location) {
        if (location == null) return;

        double[] position = new double[] {
            location.getLatitude(),
            location.getLongitude()
        };

        CurrentLocation currentLocation = new CurrentLocation(position, this::currenLocation, null);
        client.requestQueue.add(currentLocation);
        client.requestQueue.start();
    }

    public void currenLocation(City city) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onPause() {
        super.onPause();
        client.requestQueue.start();
    }
}
