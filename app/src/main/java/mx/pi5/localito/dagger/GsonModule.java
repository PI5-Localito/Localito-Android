package mx.pi5.localito.dagger;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class GsonModule {
    @Provides
    Gson provideGson() {
        return new Gson();
    };
}
