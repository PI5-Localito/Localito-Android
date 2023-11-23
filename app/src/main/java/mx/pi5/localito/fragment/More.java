package mx.pi5.localito.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        return binding.getRoot();
    }

    public void endSession(View view) {
        Auth.getInstance(getActivity()).setToken(null);
        Intent login = new Intent(getActivity(), LoginActivity.class);
        login.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(login);
        getActivity().finish();
    }
}
