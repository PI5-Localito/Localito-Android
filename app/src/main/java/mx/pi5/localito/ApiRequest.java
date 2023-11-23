package mx.pi5.localito;

import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import mx.pi5.localito.service.Auth;

abstract public class ApiRequest<T> extends Request<T> {
    static public final String BASE = "http://64.23.128.228";
    protected Response.Listener<T> listener;

    public ApiRequest(String path, int method, Response.Listener<T> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, BASE + "/api/" + path, errorListener);
        this.listener = listener;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>(super.getHeaders());
        String token = Auth.getInstance(null).getToken();
        headers.put("Authorization", String.format("Bearer %s", token));
        return headers;
    }
}
