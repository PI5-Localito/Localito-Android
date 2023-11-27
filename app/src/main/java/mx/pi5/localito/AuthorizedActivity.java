package mx.pi5.localito;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import mx.pi5.localito.activity.FrontPage;
import mx.pi5.localito.service.Auth;

abstract public class AuthorizedActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = Auth.getInstance(this).getToken();
        boolean hasToken = token != null;
        if (hasToken) return;

        Intent frontPage = new Intent(this, FrontPage.class);
        frontPage.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(frontPage);
        finish();
    }
}
