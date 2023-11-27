package mx.pi5.localito.endpoints;

import androidx.annotation.Nullable;

import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;

import mx.pi5.localito.ApiRequest;
import mx.pi5.localito.entity.Message;
import mx.pi5.localito.entity.Stand;

public class Chat extends ApiRequest<Message[]> {
    private final Gson gson = new Gson();
    public Chat(String orderId,
                Response.Listener<Message[]> listener,
                @Nullable Response.ErrorListener errorListener) {
        super(String.format("order/%s/chat", orderId), Method.GET, listener, errorListener);
    }

    @Override
    protected Response<Message[]> parseNetworkResponse(NetworkResponse response) {
        String message = new String(response.data, StandardCharsets.UTF_8);
        if (response.statusCode != 200) {
            return Response.error(new NetworkError(new Exception(message)));
        }
        Message[] data = gson.fromJson(message, Message[].class);
        return Response.success(data, null);
    }
}
