package mx.pi5.localito.endpoints;

import androidx.annotation.Nullable;

import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import mx.pi5.localito.ApiRequest;
import mx.pi5.localito.entity.Product;

public class GetProducts extends ApiRequest<Product[]> {
    private final Gson gson = new Gson();
    public GetProducts(int id, Response.Listener<Product[]> listener, @Nullable Response.ErrorListener errorListener) {
        super(String.format("stand/%d/products", id), Method.GET, listener, errorListener);
    }

    @Override
    protected Response<Product[]> parseNetworkResponse(NetworkResponse response) {
        String message = new String(response.data, StandardCharsets.UTF_8);
        Logger.getLogger("a").info(message);
        if (response.statusCode != 200) {
            return Response.error(new NetworkError(new Exception(message)));
        }
        Product[] data = gson.fromJson(message, Product[].class);
        return Response.success(data, null);
    }
}
