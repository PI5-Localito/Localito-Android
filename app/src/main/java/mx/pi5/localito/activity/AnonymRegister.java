package mx.pi5.localito.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

import mx.pi5.localito.R;
import mx.pi5.localito.databinding.AnonymRegisterBinding;
import mx.pi5.localito.endpoints.Register;
import mx.pi5.localito.entity.User;
import mx.pi5.localito.service.Client;

public class AnonymRegister extends AppCompatActivity {

    AnonymRegisterBinding b;
    private boolean passwordRequirementsVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        closeContextMenu();
        b = AnonymRegisterBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        // Botón que abre calendario para seleccionar fecha
        b.btnCalendar.setOnClickListener(v -> {
            MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Fecha de Nacimiento")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                String fecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date(selection));
                b.campoFenac.setText(fecha);
            });
            materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER_TAG");
        });

        // Configuraciones de los campos de contraseñas
        confPass();

        // Configuraciones para los Términos y Privacidad
        CheckBox ckbTerms = findViewById(R.id.ckbTerms);
        setupClickableCheckBox(ckbTerms, R.string.terms_text, R.string.terms_title, "Terms");
        CheckBox ckbPolicy = findViewById(R.id.ckbPolicy);
        setupClickableCheckBox(ckbPolicy, R.string.policy_text, R.string.policy_title, "Policy");
        b.btnRegister.setOnClickListener(view -> {
            Client client = Client.getInstance(this);
            String email = b.campoEmail.getText().toString();
            String password = b.password.getText().toString();
            String name = b.campoNombre.getText().toString();
            String lastname = b.campoApellido.getText().toString();
            User user = new User(email, password, null, name, lastname);

            client.getQueue().add(new Register(response -> {
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                this.finish();
            }, error -> Snackbar.make( b.getRoot(), error.getMessage(), Snackbar.LENGTH_SHORT).show()) {
                public String getBodyContentType() {
                    return "application/json";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    Gson gson = new Gson();
                    return gson.toJson(user).getBytes();
                }
            });
            client.getQueue().start();
        });
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

    private void confPass() {
        TextInputEditText etxPass1 = findViewById(R.id.password);
        TextInputEditText etxPass2 = findViewById(R.id.password2);
        TextView requirements = findViewById(R.id.contraReq);
        TextView passDif = findViewById(R.id.contraDif);

        // Mostrar el cumplimiento de requerimientos para la contraseña
        etxPass1.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) showPasswordRequirements();
            else hidePasswordRequirements();
        });

        // Actualiza los requerimientos mientras se escribe la contraseña
        etxPass1.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void afterTextChanged(Editable editable) {
                String password = editable.toString();
                updatePasswordRequirements(requirements, password);
            }
        });

        // Muestra texto de coincidencia de contraseñas
        etxPass2.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void afterTextChanged(Editable editable) {
                String password1 = Objects.requireNonNull(etxPass1.getText()).toString();
                String password2 = editable.toString();
                if (!password1.equals(password2)) {
                    passDif.setVisibility(View.VISIBLE);
                } else {
                    passDif.setVisibility(View.GONE);
                }
            }
        });
    }

    // Muestra requerimientos de contraseñas
    private void showPasswordRequirements() {
        if (!passwordRequirementsVisible) {
            updatePasswordRequirements(b.contraReq, Objects.requireNonNull(b.password.getText()).toString());
            passwordRequirementsVisible = true;
        }
    }

    // Oculta requerimientos de contraseñas
    private void hidePasswordRequirements() {
        if (passwordRequirementsVisible) {
            b.contraReq.setVisibility(View.GONE);
            passwordRequirementsVisible = false;
        }
    }

    // Función que actualiza los requerimientos
    private void updatePasswordRequirements(TextView textView, String password) {
        int green = ContextCompat.getColor(this, R.color.md_theme_light_secondary);
        int red = ContextCompat.getColor(this, R.color.md_theme_dark_errorContainer);

        SpannableStringBuilder builder = new SpannableStringBuilder();

        if (password.length() >= 8) {
            builder.append(getColoredSpanned("✓ Al menos 8 caracteres\n", green));
        } else {
            builder.append(getColoredSpanned("✗ Al menos 8 caracteres\n", red));
        }

        if (containsUppercase(password)) {
            builder.append(getColoredSpanned("✓ Al menos una mayúscula\n", green));
        } else {
            builder.append(getColoredSpanned("✗ Al menos una mayúscula\n", red));
        }

        if (containsLowercase(password)) {
            builder.append(getColoredSpanned("✓ Al menos una minúscula\n", green));
        } else {
            builder.append(getColoredSpanned("✗ Al menos una minúscula\n", red));
        }

        if (containsDigit(password)) {
            builder.append(getColoredSpanned("✓ Al menos un número\n", green));
        } else {
            builder.append(getColoredSpanned("✗ Al menos un número\n", red));
        }

        if (containsSpecialChar(password)) {
            builder.append(getColoredSpanned("✓ Al menos un carácter especial\n", green));
        } else {
            builder.append(getColoredSpanned("✗ Al menos un carácter especial\n", red));
        }

        if (!containsNumericSequence(password)) {
            builder.append(getColoredSpanned("✓ No secuencias numéricas\n", green));
        } else {
            builder.append(getColoredSpanned("✗ No secuencias numéricas\n", red));
        }

        textView.setVisibility(View.VISIBLE);
        textView.setText(builder);
    }

    // Hace el cambio de texto y estilos
    private SpannableString getColoredSpanned(String text, int color) {
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        spannableString.setSpan(colorSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    // Chequeo individual de requerimientos
    private boolean containsUppercase(String str) { return str.matches(".*[A-Z].*"); }
    private boolean containsLowercase(String str) { return str.matches(".*[a-z].*"); }
    private boolean containsDigit(String str) { return str.matches(".*\\d.*"); }
    private boolean containsSpecialChar(String str) { return str.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?].*"); }
    private boolean containsNumericSequence(String str) { return str.matches(".*123\\d*.*"); }

}
