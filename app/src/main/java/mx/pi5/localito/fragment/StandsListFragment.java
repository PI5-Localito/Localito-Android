package mx.pi5.localito.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.pi5.localito.R;

public class StandsListFragment extends Fragment {

    public StandsListFragment() {
        // Required empty public constructor
    }


    public static StandsListFragment newInstance(String param1, String param2) {
        StandsListFragment fragment = new StandsListFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stands, container, false);
    }
}
