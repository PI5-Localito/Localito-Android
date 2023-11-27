package mx.pi5.localito.endpoints;

import androidx.annotation.Nullable;

import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import mx.pi5.localito.ApiRequest;
import mx.pi5.localito.entity.Order;
import mx.pi5.localito.entity.Stand;

public class Orders extends ApiRequest<Order>{

    private final Gson gson = new Gson();
    public Orders(int standId,
                  Response.Listener<Order> listener,
                  @Nullable Response.ErrorListener errorListener){
        super(String.format("stand/%d/orders/create", standId), Method.POST, listener, errorListener);
    }

    @Override
    protected Response<Order> parseNetworkResponse(NetworkResponse response){
        String message = new String(response.data, StandardCharsets.UTF_8);
        if (response.statusCode != 200) {
            return Response.error(new NetworkError(new Exception(message)));
        }
        Order data = gson.fromJson(message, Order.class);
        return Response.success(data, null);
    }
}
