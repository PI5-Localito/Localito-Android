package mx.pi5.localito.service;

import com.google.android.material.textfield.TextInputLayout;

public class Verifier {
    public static boolean fieldEmpty(String value, TextInputLayout field) {
        if (value.isEmpty()) {
            field.setError("Fill this input");
            return true;
        }
        field.setError(null);
        return false;
    }
}
