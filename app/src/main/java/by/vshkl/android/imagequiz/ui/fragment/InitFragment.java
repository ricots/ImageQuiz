package by.vshkl.android.imagequiz.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
import by.vshkl.android.imagequiz.utils.DialogUtils;
import by.vshkl.android.imagequiz.utils.PrefUtils;

public class InitFragment extends MvpAppCompatFragment implements InitView, OnClickListener {

    private static final String KEY_CHANGE_PLAYER = "OffersFragment.KEY_CHANGE_PLAYER";

    @InjectPresenter InitPresenter presenter;

    private Toolbar tbToolbar;
    private EditText etName;
    private LinearLayout llEmpty;
    private LinearLayout llProgress;
    private LinearLayout llStart;
    private Button btnStart;

    private MainActivity parentActivity;
    private boolean isChangePlayer;

    public static Fragment newInstance(boolean isChangePlayer) {
        Fragment fragment = new InitFragment();
        Bundle args = new Bundle();
        args.putBoolean(KEY_CHANGE_PLAYER, isChangePlayer);
        fragment.setArguments(args);
        return fragment;
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
        isChangePlayer = getArguments().getBoolean(KEY_CHANGE_PLAYER, false);
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
        llStart = (LinearLayout) view.findViewById(R.id.ll_start);
        btnStart = (Button) view.findViewById(R.id.btn_start);

        llEmpty.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        parentActivity.setSupportActionBar(tbToolbar);

        if (isChangePlayer) {
            presenter.showStart();
        } else {
            presenter.showEmpty();
        }
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_empty:
                presenter.downloadQuiz();
                break;
            case R.id.btn_start:
                presenter.startQuiz();
                break;
        }
    }

    @Override
    public void showEmpty() {
        llProgress.setVisibility(View.GONE);
        llStart.setVisibility(View.GONE);
        llEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        llStart.setVisibility(View.GONE);
        llEmpty.setVisibility(View.GONE);
        llProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showStart() {
        llEmpty.setVisibility(View.GONE);
        llProgress.setVisibility(View.GONE);
        llStart.setVisibility(View.VISIBLE);
    }

    @Override
    public void showQuiz() {
        String name = etName.getText().toString();

        if (name.isEmpty()) {
            DialogUtils.showEmptyNameAlert(getContext());
        } else {
            PrefUtils.setLogged(getContext(), true);
            PrefUtils.setName(getContext(), name);
            parentActivity.getPresenter().showQuiz();
        }
    }
}
