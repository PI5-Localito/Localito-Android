package mx.pi5.localito.service;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AuthManager {
    private SharedPreferences sharedPreferences;

    @Inject
    AuthManager(SharedPreferences preferences) {
        sharedPreferences = preferences;
    }

    public String getToken() {
        return sharedPreferences.getString("token", null);
    }

    public void setToken(String token) {
        sharedPreferences.edit().putString("token", token).apply();
    }
}
