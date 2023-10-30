package mx.pi5.localito.services;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequest {
    public static String getJsonFromApi(String apiUrl) {
        StringBuilder jsonResult = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            // abre conexión
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Log.d("DEBUG_INSANO", "ApiRequest.java(17): "+connection.toString());
            connection.setRequestMethod("GET");
            Log.d("DEBUG_INSANO", "ApiRequest.java(19): "+connection);

            // lectura de respuesta api
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Log.d("DEBUG_INSANO", "ApiRequest.java(23): "+ reader);
            String line;
            Integer n=1;
            while ((line = reader.readLine()) != null) {
                Log.d("DEBUG_INSANO", "ApiRequest.java(27): #"+n+" - "+line);
                jsonResult.append(line);
                n++;
            }
            reader.close();
            Log.d("DEBUG_INSANO", "ApiRequest.java(32): "+jsonResult);

            // cierra conexión
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("DEBUG_INSANO", "ApiRequest.java(40): Json Return - "+ jsonResult);
        return jsonResult.toString();
    }
}
