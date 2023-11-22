package mx.pi5.localito.service;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyClient {
    private static VolleyClient instance = null;
    private VolleyClient() {

    }

    static public synchronized  VolleyClient getInstance() {
        if (instance == null) {
            instance = new VolleyClient();
        }
        return instance;
    }

    public RequestQueue getQueue() {
    }
}
