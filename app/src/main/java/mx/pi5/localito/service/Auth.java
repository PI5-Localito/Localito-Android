package mx.pi5.localito.service;

import android.content.Context;
import android.content.SharedPreferences;

public class Auth {
    static public Auth instance = null;
    private SharedPreferences sharedPreferences;

    protected Auth(SharedPreferences preferences) {
        sharedPreferences = preferences;
    }

    static public Auth getInstance(Context ctx) {
        if (instance == null) {
            SharedPreferences preferences = ctx.getSharedPreferences("mx.pi5.localito.auth", Context.MODE_PRIVATE);
            instance = new Auth(preferences);
        }
        return instance;
    }

    public String getToken() {
        return sharedPreferences.getString("token", null);
    }

    public Auth setToken(String token) {
        sharedPreferences.edit().putString("token", token).apply();
        return this;
    }
}
