package by.vshkl.android.imagequiz.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import by.vshkl.android.imagequiz.R;
import by.vshkl.android.imagequiz.mvp.model.Score;
import by.vshkl.android.imagequiz.mvp.presenter.RatingPresenter;
import by.vshkl.android.imagequiz.mvp.view.RatingView;
import by.vshkl.android.imagequiz.ui.activity.MainActivity;
import by.vshkl.android.imagequiz.ui.adapter.RatingAdapter;
import by.vshkl.android.imagequiz.utils.PrefUtils;

public class RatingFragment extends MvpAppCompatFragment implements RatingView {

    @InjectPresenter RatingPresenter presenter;

    private Toolbar tbToolbar;
    private RecyclerView rvList;

    private MainActivity parentActivity;
    private RatingAdapter ratingAdapter;

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
        rvList = (RecyclerView) view.findViewById(R.id.rv_rating);
        parentActivity.setSupportActionBar(tbToolbar);
        initializeRecyclerView();
        presenter.loadRating(PrefUtils.getName(getContext()));
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
    public void showRating(List<Score> scores, String scoreTitle) {
        parentActivity.getSupportActionBar().setTitle(scoreTitle);
        ratingAdapter.setScores(scores);
        ratingAdapter.notifyDataSetChanged();
    }

    private void initializeRecyclerView() {
        ratingAdapter = new RatingAdapter();
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvList.setAdapter(ratingAdapter);
    }
}
