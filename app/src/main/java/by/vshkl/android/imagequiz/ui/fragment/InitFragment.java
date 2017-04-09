package by.vshkl.android.imagequiz.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import by.vshkl.android.imagequiz.R;
import by.vshkl.android.imagequiz.mvp.presenter.InitPresenter;
import by.vshkl.android.imagequiz.mvp.view.InitView;
import by.vshkl.android.imagequiz.ui.activity.MainActivity;

public class InitFragment extends MvpAppCompatFragment implements InitView {

    @InjectPresenter InitPresenter presenter;

    private Toolbar tbToolbar;
    private EditText etName;
    private LinearLayout llEmpty;
    private LinearLayout llProgress;
    private Button btnStart;

    private MainActivity parentActivity;

    public static Fragment newInstance() {
        return new InitFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.parentActivity = (MainActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_init, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tbToolbar = (Toolbar) view.findViewById(R.id.tb_toolbar);
        etName = (EditText) view.findViewById(R.id.et_name);
        llEmpty = (LinearLayout) view.findViewById(R.id.ll_empty);
        llProgress = (LinearLayout) view.findViewById(R.id.ll_progress);
        btnStart = (Button) view.findViewById(R.id.btn_start);
        parentActivity.setSupportActionBar(tbToolbar);
    }

    @Override
    public void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    public void onDetach() {
        this.parentActivity = null;
        super.onDetach();
    }
}
