package mx.pi5.localito.endpoints;

import androidx.annotation.Nullable;

import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;

import mx.pi5.localito.ApiRequest;
import mx.pi5.localito.entity.Stand;

public class GetStandsByCategory extends ApiRequest<Stand[]> {
    private final Gson gson = new Gson();
    public GetStandsByCategory(String category,
                               Response.Listener<Stand[]> listener,
                               @Nullable Response.ErrorListener errorListener) {
        super(String.format("/stands/category/%s", category), Method.GET, listener, errorListener);
    }

    @Override
    protected Response<Stand[]> parseNetworkResponse(NetworkResponse response) {
        String message = new String(response.data, StandardCharsets.UTF_8);
        if (response.statusCode != 200) {
            return Response.error(new NetworkError(new Exception(message)));
        }
        Stand[] data = gson.fromJson(message, Stand[].class);
        return Response.success(data, null);
    }
}
