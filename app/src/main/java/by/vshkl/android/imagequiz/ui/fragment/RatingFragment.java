package by.vshkl.android.imagequiz.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import by.vshkl.android.imagequiz.R;
import by.vshkl.android.imagequiz.ui.activity.MainActivity;

public class RatingFragment extends MvpAppCompatFragment {

    private Toolbar tbToolbar;

    private MainActivity parentActivity;

    public static Fragment newInstance() {
        return new RatingFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.parentActivity = (MainActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rating, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tbToolbar = (Toolbar) view.findViewById(R.id.tb_toolbar);
        parentActivity.setSupportActionBar(tbToolbar);
    }

    @Override
    public void onDetach() {
        this.parentActivity = null;
        super.onDetach();
    }
}
