package mx.pi5.localito.services;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import mx.pi5.localito.MainActivity;

/*
Ejemplo para usar en código
    upload.setOnClickListener(view -> {
        if (currentImagePath != null) {
            new ImageUploader(MainActivity.this).execute(currentImagePath);
        } else {
            Toast.makeText(this, "No hay imagen para subir", Toast.LENGTH_SHORT).show();
        }
    });
*/
public class ImageUploader extends AsyncTask<String, Void, String> {

    private WeakReference<MainActivity> activityReference;

    public ImageUploader(MainActivity activity) {
        this.activityReference = new WeakReference<>(activity);
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("DEBUG_IUT27", "doInBackground: Inicio");
        try {
            Log.d("DEBUG_IU.38", "Entro al try");
            URL url = new URL("URL_DEL_SERVIDOR"); // TODO cambiar esta URL

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                Log.d("DEBUG_IUT37", "Entro al segundo try");
                String imagePath = params[0];
                try (FileInputStream fileInputStream = new FileInputStream(imagePath)) {
                    Log.d("DEBUG_IU.49", "Entro al tercer try");
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return "Imagen subida exitosamente";
            } else {
                return "Error al subir la imagen. Código de respuesta: " + responseCode;
            }
        } catch (IOException e) {
            Log.e("ImageUploader", "Error al subir la imagen", e);
            return "Error al subir la imagen. Detalles: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("ImageUploader", result);
        MainActivity activity = activityReference.get();
        if (activity != null) {
            Toast.makeText(activity, result, Toast.LENGTH_LONG).show();
            Log.d("DEGUB_IU.76", result);
        }
    }
}
