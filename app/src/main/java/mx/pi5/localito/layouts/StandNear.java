package mx.pi5.localito.layouts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mx.pi5.localito.R;
import mx.pi5.localito.databinding.ActivityStandNearBinding;


public class StandNear extends AppCompatActivity {
    ActivityStandNearBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityStandNearBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        AssetManager assetManager = getAssets();

        try {
            // Lee el archivo JSON desde la carpeta "assets"
            InputStream inputStream = assetManager.open("sample.json");

            // Convierte el InputStream en una cadena
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String json = stringBuilder.toString();

            // Parsea el JSON y almacena los datos en una lista
            JSONArray jsonArray = new JSONArray(json);
            List<YourDataModel> dataList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Extraer datos del JSON (título, descripción e imagen)
                String title = jsonObject.optString("tit");
                String description = jsonObject.optString("desc");
                String imageUrl = jsonObject.optString("ubi");

                // Crear una instancia de tu modelo de datos
                YourDataModel dataModel = new YourDataModel(title, description, imageUrl);

                // Agregar el modelo a la lista
                dataList.add(dataModel);
            }

            // Configura el RecyclerView y el adaptador con los datos
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            YourAdapter adapter = new YourAdapter(this, dataList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    public class YourDataModel {
        private String title;
        private String description;
        private String imageUrl;

        public YourDataModel(String title, String description, String imageUrl) {
            this.title = title;
            this.description = description;
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public class YourAdapter extends RecyclerView.Adapter<YourAdapter.ViewHolder> {
        private List<YourDataModel> dataList;
        private Context context;

        // Debes pasar el contexto en el constructor
        public YourAdapter(Context context, List<YourDataModel> dataList) {
            this.context = context;
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_stand_near, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            YourDataModel data = dataList.get(position);

            // Configura las vistas dentro de tu card con los datos
            holder.cardTitle.setText(data.getTitle());
            holder.cardDescription.setText(data.getDescription());

        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView cardImage;
            TextView cardTitle;
            TextView cardDescription;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                cardImage = itemView.findViewById(R.id.card_image);
                cardTitle = itemView.findViewById(R.id.card_title);
                cardDescription = itemView.findViewById(R.id.card_description);
            }
        }
    }
}
