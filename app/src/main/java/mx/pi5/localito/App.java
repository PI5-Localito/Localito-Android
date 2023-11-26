package mx.pi5.localito;

import android.app.Application;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import dagger.Component;
import dagger.hilt.android.HiltAndroidApp;
import dagger.hilt.components.SingletonComponent;

@HiltAndroidApp
public class App extends Application {
    static public final String API_URL = "http://192.168.0.36:8000";
    @Override
    public void onCreate() {
        super.onCreate();
        GoogleApiAvailability gapi_av = GoogleApiAvailability.getInstance();
        if (gapi_av.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {
        }
    }
}
