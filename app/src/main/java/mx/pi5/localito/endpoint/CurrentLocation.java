package mx.pi5.localito.endpoint;

import androidx.annotation.Nullable;

import com.android.volley.Response;

import mx.pi5.localito.ApiJsonRequest;
import mx.pi5.localito.entity.City;
import mx.pi5.localito.entity.GeoJsonPoint;

public class CurrentLocation extends ApiJsonRequest<double[], City> {
    public CurrentLocation(double[] body, Response.Listener<City> listener, @Nullable Response.ErrorListener errorListener) {
        super("/api/location", Method.POST, body, listener, errorListener);
    }
}
