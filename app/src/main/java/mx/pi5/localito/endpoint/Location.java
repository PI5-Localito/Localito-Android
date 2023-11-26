package mx.pi5.localito.endpoint;

import androidx.annotation.Nullable;

import com.android.volley.Response;

import mx.pi5.localito.ApiJsonRequest;
import mx.pi5.localito.entity.GeoJsonPoint;

public class Location extends ApiJsonRequest<double[], GeoJsonPoint> {
    public Location(double[] body, Response.Listener<GeoJsonPoint> listener, @Nullable Response.ErrorListener errorListener) {
        super("/api/location", Method.POST, body, listener, errorListener);
    }
}
