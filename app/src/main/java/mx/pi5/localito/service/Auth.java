package mx.pi5.localito.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Base64;

import mx.pi5.localito.entity.User;

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

    public User getData() {
        String token = getToken();
        String[] parts = token.split(".", 3);
        String data = parts[1];
        byte[] bts = Base64.getDecoder().decode(data);
        Gson gson = new Gson();
        return gson.fromJson(Arrays.toString(bts), User.class);
    }
}
