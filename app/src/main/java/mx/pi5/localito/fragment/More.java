package mx.pi5.localito.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import mx.pi5.localito.R;
import mx.pi5.localito.activity.LoginActivity;
import mx.pi5.localito.databinding.FragmentMoreBinding;
import mx.pi5.localito.service.Auth;

public class More extends Fragment {
    FragmentMoreBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentMoreBinding.inflate(inflater, container, false);
        binding.logout.setOnClickListener(this::endSession);
        binding.privacy.setOnClickListener(v -> showDialog("Policy"));
        binding.terms.setOnClickListener(v -> showDialog("Terms"));
        return binding.getRoot();
    }

    public void endSession(View view) {
        Auth.getInstance(getActivity()).setToken(null);
        Intent login = new Intent(getActivity(), LoginActivity.class);
        login.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(login);
        getActivity().finish();
    }

    private String convertStreamToString(BufferedReader reader) {
        try {
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

    private void showDialog(String name) {
        String contentResourceName = name.toLowerCase() + "_content";
        String titleResourceName = name.toLowerCase() + "_title";

        int rawResourceId = getResources().getIdentifier(contentResourceName, "raw", requireContext().getPackageName());
        int titleResourceId = getResources().getIdentifier(titleResourceName, "string", requireContext().getPackageName());

        if (rawResourceId == 0 || titleResourceId == 0) { return; }

        try (InputStream inputStream = getResources().openRawResource(rawResourceId);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String content = convertStreamToString(reader);
            Spanned formattedContent = HtmlCompat.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY);

            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setTitle(titleResourceId)
                .setMessage(formattedContent)
                .setPositiveButton("Cerrar", (dialog, id) -> dialog.dismiss());
            builder.create().show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
