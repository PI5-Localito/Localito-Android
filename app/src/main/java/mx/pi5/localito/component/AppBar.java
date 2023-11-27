package mx.pi5.localito.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.NonNull;

import com.google.android.material.appbar.AppBarLayout;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ViewScoped;
import mx.pi5.localito.R;
import mx.pi5.localito.databinding.ComponentAppbarBinding;

@ViewScoped
public class AppBar extends AppBarLayout {
    ComponentAppbarBinding binding;
    boolean isCollapsed;

    @Inject
    public AppBar(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.component_appbar, this);
        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.AppBar);
        isCollapsed = styledAttributes.getBoolean(R.styleable.AppBar_collapse, false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        binding = ComponentAppbarBinding.bind(this);
        if (isCollapsed) collapseAppbar();
    }

    public void collapseAppbar() {
        Toolbar.LayoutParams params = (Toolbar.LayoutParams) binding.brand.getLayoutParams();
        params.gravity = Gravity.CENTER;
        binding.avatar.setVisibility(INVISIBLE);
        binding.locationContainer.setVisibility(INVISIBLE);
    }
}
