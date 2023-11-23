package mx.pi5.localito.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.HashMap;

import mx.pi5.localito.databinding.ActivityLoginBinding;
import mx.pi5.localito.endpoints.Login;
import mx.pi5.localito.service.Auth;
import mx.pi5.localito.service.Client;
import mx.pi5.localito.service.Verifier;

public class LoginActivity extends AppCompatActivity {
    protected Gson gson = new Gson();
    ActivityLoginBinding b;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        b.btnLogin.setOnClickListener(view -> {
            EditText email = b.inputEmail.getEditText();
            EditText password = b.inputPassword.getEditText();
            assert email != null && password != null;

            String mail = email.getText().toString().trim();
            String passw = password.getText().toString().trim();
            if (Verifier.fieldEmpty(mail, b.inputEmail)) return;
            if (Verifier.fieldEmpty(passw, b.inputPassword)) return;
            Request<String> request = new Login(this::manageToken, this::snackResponse) {
                @Override
                public String getBodyContentType() {
                    return "application/json";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("email", mail);
                    map.put("password", passw);
                    return gson.toJson(map).getBytes();
                }
            };
            Client.getInstance(this).getQueue().add(request);
            Client.getInstance(this).getQueue().start();
        });

        b.btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, AnonymRegister.class);
            startActivity(intent);
        });
    }

    public void snackResponse(VolleyError error) {
        String message = error.getMessage();
        if (message == null) message = "Fallo al iniciar sesion";
        Snackbar.make(b.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }

    public void manageToken(String response) {
        Intent login = new Intent(this, MainActivity.class);
        login.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(login);
        Auth.getInstance(this).setToken(response);
        finish();
    }
}
