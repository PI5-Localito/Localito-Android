package mx.pi5.localito.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import mx.pi5.localito.R;
import mx.pi5.localito.databinding.AnonymRegisterBinding;

public class AnonymRegister extends AppCompatActivity {

    AnonymRegisterBinding b;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        closeContextMenu();
        b = AnonymRegisterBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        // Configuraciones para los Términos y Privacidad
        CheckBox ckbTerms = findViewById(R.id.ckbTerms);
        setupClickableCheckBox(ckbTerms, R.string.terms_text, R.string.terms_title, "Terms");
        CheckBox ckbPolicy = findViewById(R.id.ckbPolicy);
        setupClickableCheckBox(ckbPolicy, R.string.policy_text, R.string.policy_title, "Policy");
        b.
    }

    // Opciones del menú superior
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Configuración de los CheckBox, les pone texto y llama a dialogos
    private void setupClickableCheckBox(CheckBox checkBox, int textResId, int titleResId, String name) {
        int clickableTextColor = ContextCompat.getColor(this, R.color.seed);
        String text = getString(textResId);
        String title = getString(titleResId);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                widget.cancelPendingInputEvents();
                showDialog(name);
            }
        };

        ForegroundColorSpan textColorSpan = new ForegroundColorSpan(clickableTextColor);

        SpannableString linkText = new SpannableString(title);
        linkText.setSpan(clickableSpan, 0, linkText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        linkText.setSpan(textColorSpan, 0, linkText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CharSequence cs = TextUtils.expandTemplate(text + " ^1", linkText);

        checkBox.setText(cs);
        checkBox.setMovementMethod(LinkMovementMethod.getInstance());
    }

    // Para los diálogos de Términos y Privacidad
    private void showDialog(String name) {
        String contentResourceName = name.toLowerCase() + "_content";
        String titleResourceName = name.toLowerCase() + "_title";
        String checkBoxName = "ckb" + name;

        int rawResourceId = getResources().getIdentifier(contentResourceName, "raw", getPackageName());
        InputStream inputStream = getResources().openRawResource(rawResourceId);

        int titleResourceId = getResources().getIdentifier(titleResourceName, "string", getPackageName());

        int checkBoxResourceId = getResources().getIdentifier(checkBoxName, "id", getPackageName());

        String content = convertStreamToString(inputStream);
        Spanned formattedContent = Html.fromHtml(content);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titleResourceId)
            .setMessage(formattedContent)
            .setPositiveButton("Aceptar", (dialog, id) -> {
                CheckBox checkBox = findViewById(checkBoxResourceId);
                if (checkBox != null) {
                    checkBox.setChecked(true);
                }
                dialog.dismiss();
            })
            .setNegativeButton("Cerrar", (dialog, id) -> dialog.dismiss());
        builder.create().show();
    }

    // Para los textos
    private String convertStreamToString(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
