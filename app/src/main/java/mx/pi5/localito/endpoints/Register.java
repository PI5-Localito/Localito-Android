package mx.pi5.localito.endpoints;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.nio.charset.StandardCharsets;

import mx.pi5.localito.ApiRequest;
import mx.pi5.localito.entity.Buyer;
import mx.pi5.localito.entity.User;

class UserBuyer {
    public User user;
    public Buyer buyer;
}

public class Register extends ApiRequest<String> {

    public Register(Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super("user/create", Method.POST, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String message = new String(response.data, StandardCharsets.UTF_8);
        if (response.statusCode != 200) {
            return Response.error(new VolleyError(message));
        } else {
            return Response.success(message, null);
        }
    }

}
