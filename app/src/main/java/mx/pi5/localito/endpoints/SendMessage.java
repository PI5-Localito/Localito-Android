package mx.pi5.localito.endpoints;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import mx.pi5.localito.ApiRequest;

public class SendMessage extends ApiRequest<String> {

    public SendMessage(String body, int order, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(String.format("order/%d/newmessage", order), Method.POST, listener, errorListener);
        this.body = body;
    }

    public String body;

    @Override
    public String getBodyContentType() {
        return "application/json";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<>();
        map.put("body", body);
        return gson.toJson(map).getBytes();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String message = new String(response.data, StandardCharsets.UTF_8);
        if (response.statusCode != 200) return Response.error(new VolleyError(message));
        return Response.success(message, null);
    }
}
