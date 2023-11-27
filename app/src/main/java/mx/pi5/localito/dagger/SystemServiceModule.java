package mx.pi5.localito.dagger;

import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiManager;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;

@Module
@InstallIn(ActivityComponent.class)
public class SystemServiceModule {
    @Provides
    WifiManager provideWifiManager(@ActivityContext Context context) {
        return context.getSystemService(WifiManager.class);
    }
    @Provides
    LocationManager provideLocationManager(@ActivityContext Context context) {
        return context.getSystemService(LocationManager.class);
    }
}
