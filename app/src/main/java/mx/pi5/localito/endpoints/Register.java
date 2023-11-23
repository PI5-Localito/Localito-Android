package mx.pi5.localito.endpoints;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import mx.pi5.localito.ApiRequest;
import mx.pi5.localito.entity.Buyer;
import mx.pi5.localito.entity.User;


public class Register extends ApiRequest<Register.UserBuyer> {
    public class UserBuyer {
        public User user;
        public Buyer buyer;
    }

    public Gson gson = new Gson();
    public Register(Response.Listener<UserBuyer> listener, @Nullable Response.ErrorListener errorListener) {
        super("user/create", Method.POST, listener, errorListener);
    }

    @Override
    protected Response<UserBuyer> parseNetworkResponse(NetworkResponse response) {
        String message = new String(response.data, StandardCharsets.UTF_8);
        if (response.statusCode != 200) return Response.error(new VolleyError(message));
        return Response.success(gson.fromJson(message, UserBuyer.class), null);
    }
}
