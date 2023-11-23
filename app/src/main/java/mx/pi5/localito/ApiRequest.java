package mx.pi5.localito;

import com.android.volley.Request;

import java.nio.file.Paths;
import java.util.StringJoiner;

abstract public class ApiRequest<T> extends Request<T> {
    static public final String URL = "127.0.0.1:8000/api";
    public ApiRequest(String path, int method) {
        super(method, new StringJoiner("/").add(URL).add(path).toString(), null);
    }
}
