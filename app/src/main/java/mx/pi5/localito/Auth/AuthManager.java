package mx.pi5.localito.Auth;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import net.openid.appauth.AppAuthConfiguration;
import net.openid.appauth.AppAuthConfiguration.Builder;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.connectivity.ConnectionBuilder;
import net.openid.appauth.connectivity.DefaultConnectionBuilder;

import net.openid.appauth.ResponseTypeValues;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;

import java.security.PrivateKey;
import java.security.PrivilegedAction;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AuthManager {
    private static final int REQUEST_CODE_AUTH = 1001;
    private final Context context;
    private AuthorizationService authorizationService;
    private AuthorizationServiceConfiguration serviceConfiguration;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private long tokenExpirationTime;
    public AuthManager(Context context, String clientId, String clientSecret, String redirectUri) {
        this.context = context;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;

        // Configurar la biblioteca AppAuth sin especificar la conexión
        AppAuthConfiguration appAuthConfiguration = new AppAuthConfiguration.Builder().build();
        AuthorizationService authorizationService = new AuthorizationService(context, appAuthConfiguration);

        // Configurar la URL del servicio de autorización y token
        serviceConfiguration = new AuthorizationServiceConfiguration(
            Uri.parse("https://example.com/authorize"), // Authorization endpoint
            Uri.parse("https://example.com/token")      // Token endpoint
        );
    }

    public void authenticate() {
        AuthorizationRequest authRequest = new AuthorizationRequest.Builder(
            serviceConfiguration,
            clientId,
            ResponseTypeValues.CODE,
            Uri.parse(redirectUri)
        ).build();

        Intent intent = authorizationService.getAuthorizationRequestIntent(authRequest);
        ((AppCompatActivity) context).startActivityForResult(intent, REQUEST_CODE_AUTH);
    }

    public void handleAuthorizationResponse(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_AUTH) {
            AuthorizationResponse authResponse = AuthorizationResponse.fromIntent(data);
            AuthorizationException authException = AuthorizationException.fromIntent(data);

            if (authResponse != null) {
                // Authorization successful, exchange code for token
                retrieveTokens(authResponse);
            } else if (authException != null) {
                // Authorization failed
                Toast.makeText(context, "Authorization failed: " + authException.errorDescription, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void retrieveTokens(AuthorizationResponse authResponse) {
        TokenRequest tokenRequest = authResponse.createTokenExchangeRequest();

        authorizationService.performTokenRequest(
            tokenRequest,
            new AuthorizationService.TokenResponseCallback() {
                @Override
                public void onTokenRequestCompleted(TokenResponse tokenResponse, AuthorizationException tokenException) {
                    if (tokenResponse != null) {
                        // Token exchange successful, handle tokens
                        handleTokenResponse(tokenResponse);
                    } else if (tokenException != null) {
                        // Token exchange failed
                        Toast.makeText(context, "Token exchange failed: " + tokenException.errorDescription, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        );
    }

    private void handleTokenResponse(TokenResponse tokenResponse) {
        // Handle token response, you may want to persist tokens or perform additional actions

        Long expiresIn = tokenResponse.accessTokenExpirationTime;

        if(expiresIn != null) {
            tokenExpirationTime = expiresIn;

            // For simplicity, we'll just print the access token in this example
            Log.d("AccessToken", tokenResponse.accessToken);
            // Now you can make API calls using the access token
            executeApiCall(tokenResponse.accessToken);
        }
    }
    private boolean isTokenValid() {
        // Verificar si el token ha expirado comparando con el tiempo actual
        return System.currentTimeMillis() < tokenExpirationTime;
    }

    private void executeApiCall(String accessToken) {

        if(isTokenValid()){
            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... params) {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                        .url("https://example.com/api/...")
                        .addHeader("Authorization", String.format("Bearer %s", params[0]))
                        .build();

                    try {
                        Response response = client.newCall(request).execute();
                        return response.body().string();
                    } catch (Exception e) {
                        // Handle API error here
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(String response) {
                    // Handle API response here
                    if (response != null) {
                        Log.d("ApiResponse", response);
                    } else {
                        Log.e("ApiError", "Error in API call");
                    }
                }
            }.execute(accessToken);
        }else {
            // Si el token ha expirado, renueva el token antes de hacer la llamada a la API
            renewToken();
        }

    }

    private void renewToken(){
        authenticate();
    }
}
