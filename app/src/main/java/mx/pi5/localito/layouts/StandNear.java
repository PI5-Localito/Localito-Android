package mx.pi5.localito.layouts;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.pi5.localito.R;
import mx.pi5.localito.services.Adaptador;
import mx.pi5.localito.services.ApiRequest;

public class StandNear extends AppCompatActivity {
    RecyclerView recyclerView;
    Adaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_near);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adaptador();
        recyclerView.setAdapter(adapter);

        new GetDataFromApiTask().execute();
    }

    public class GetDataFromApiTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            // solicitud a la API y respuesta JSON como cadena
            String apiUrl = "https://653b29a72e42fd0d54d4bf04.mockapi.io/api/testv1/stands";
            return ApiRequest.getJsonFromApi(apiUrl);
        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            if (jsonResponse != null) {
                // analiza respuesta JSON y actualiza RecyclerView
                try {
                    List<Stand> stands = parseJson(jsonResponse);
                    adapter.setStands(stands);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Stand> parseJson(String json) throws JSONException {
        List<Stand> stands = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Stand stand = new Stand();
            stand.title = jsonObject.getString("title");
            stand.description = jsonObject.getString("description");
            stand.imageUrl = jsonObject.getString("image");
            stand.id = jsonObject.getString("id");
            stands.add(stand);
        }
        return stands;
    }

    public static class Stand {
        private String title;
        private String description;
        private String imageUrl;
        private String id;

        public Stand(String title, String description, String imageUrl, String id) {
            this.title = title;
            this.description = description;
            this.imageUrl = imageUrl;
            this.id = id;
        }
        public Stand() { }
        public String getTitle() { return title;}
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
    }
}
