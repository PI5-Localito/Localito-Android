package mx.pi5.localito.endpoints;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import mx.pi5.localito.ApiRequest;

public class Login extends ApiRequest<String> {

    public Login(Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super("token", Method.POST, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String message = new String(response.data, StandardCharsets.UTF_8);
        if (response.statusCode != 200) return Response.error(new VolleyError(message));
        return Response.success(message, null);
    }
}
