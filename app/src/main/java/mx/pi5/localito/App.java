package mx.pi5.localito;

import android.app.Application;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import javax.inject.Inject;

import dagger.Component;
import dagger.hilt.android.HiltAndroidApp;
import dagger.hilt.components.SingletonComponent;
import mx.pi5.localito.service.ApiClient;

@HiltAndroidApp
public class App extends Application {
    @Inject
    ApiClient client;


    @Override
    public void onCreate() {
        super.onCreate();
        GoogleApiAvailability gapi_av = GoogleApiAvailability.getInstance();

        if (gapi_av.isGooglePlayServicesAvailable(this) != ConnectionResult.SUCCESS) {
            // Toast.makeText(, "", Toast.LENGTH_SHORT).show();
        }
    }
}
