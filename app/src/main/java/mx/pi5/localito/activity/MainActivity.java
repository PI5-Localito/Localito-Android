package mx.pi5.localito.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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
    WifiManager wifiManager;
    @Inject
    LocationManager locationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkPermissions();
        getLocation();
    }

    protected checkPermissions() {
        int locationPermission = checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION);

        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 102);
            return null;
        }

    }

    public Location getLocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 102);
            return null;
        }
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this)
                .setCancelable(false)
                .setTitle("Ubicacion necesaria")
                .setMessage("Se necesita el servicio de ubicacion para una mejor experiencia")
                .setNeutralButton("Reintentar", null);
            AlertDialog alert = dialog.show();
            alert.getButton(DialogInterface.BUTTON_NEUTRAL)
                .setOnClickListener(v -> {
                    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        alert.dismiss();
                        return;
                    }
                    Toast.makeText(this, "No esta habilitado la localizacion", Toast.LENGTH_SHORT).show();
                });
        }
        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
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
        client.requestQueue.stop();
    }
}
