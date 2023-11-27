package mx.pi5.localito;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import mx.pi5.localito.service.ApiClient;
import mx.pi5.localito.service.AuthManager;


abstract public class ApiJsonRequest<T, RT> extends Request<RT> {
    @Inject
    AuthManager authManager;
    @Inject
    Gson gson;

    protected Response.Listener<RT> listener;
    protected T body;

    public ApiJsonRequest(
        String path, int method, T body,
        Response.Listener<RT> listener,
        @Nullable Response.ErrorListener errorListener
    ) {
        super(method, ApiClient.URL + path, errorListener);
        this.listener = listener;
    }

    @Override
    protected void deliverResponse(RT response) {
        listener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>(super.getHeaders());
        String token = authManager.getToken();
        headers.put("Authorization", String.format("Bearer %s", token));
        return headers;
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return gson.toJson(body).getBytes();
    }

    @Override
    protected Response<RT> parseNetworkResponse(NetworkResponse response) {
        String message = new String(response.data, StandardCharsets.UTF_8);
        if (response.statusCode != 200) return Response.error(new VolleyError(message));
        TypeToken<RT> token = new TypeToken<RT>() {};
        RT data = gson.fromJson(message, token.getType());
        return Response.success(data, null);
    }
}
