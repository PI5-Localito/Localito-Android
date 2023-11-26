package mx.pi5.localito;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import javax.inject.Inject;

import mx.pi5.localito.service.ApiClient;

abstract  public class LoaderActivity<T> extends AppCompatActivity {
    @Inject
    ApiClient client;

    abstract void dataLoader(T response);
    abstract void loaderError(VolleyError error);

    @Override
    protected void onResume() {
        super.onResume();
        client.requestQueue.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        client.requestQueue.stop();
    }
}
