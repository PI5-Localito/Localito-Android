package mx.pi5.localito.endpoints;

import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;

import mx.pi5.localito.ApiRequest;
import mx.pi5.localito.entity.Stand;

abstract public class GetStands extends ApiRequest<Stand[]> {
    private final Gson gson = new Gson();
    public GetStands() {
        super("/stands", Method.GET);
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
