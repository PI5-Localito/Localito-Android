package mx.pi5.localito.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.google.android.material.appbar.AppBarLayout;


import javax.inject.Inject;

import dagger.hilt.android.scopes.ViewScoped;
import mx.pi5.localito.R;
import mx.pi5.localito.databinding.ComponentAppbarBinding;

@ViewScoped
public class AppBar extends AppBarLayout {
    ComponentAppbarBinding binding;

    @Inject
    public AppBar(@NonNull Context context, AttributeSet attrs) {
        super(context);
        inflate(context, R.layout.component_appbar, this);
        binding = ComponentAppbarBinding.bind(this);
        attrs.getAttributeBooleanValue('Centered');
    }

}
