package mx.pi5.localito;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import javax.inject.Inject;

import mx.pi5.localito.entity.MessageResponse;
import mx.pi5.localito.service.ApiClient;

abstract  public class LoaderActivity<T> extends AppCompatActivity {
    @Inject
    ApiClient client;

    @Inject
    Gson gson;

    abstract void dataLoader(T response);
    public void loaderError(VolleyError error) {
        String response = error.getMessage();
        MessageResponse data = gson.fromJson(response, MessageResponse.class);
        Toast.makeText(this, data.message, Toast.LENGTH_SHORT).show();
    }

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
