package mx.pi5.localito;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.logging.Logger;

abstract public class ApiRequest<T> extends Request<T> {
    static public final String URL = "http://64.23.128.228/api";

    public ApiRequest(String path, int method) {
        super(method, URL + path, error -> Logger.getLogger("Moment").info(error.getMessage()));
    }
}
