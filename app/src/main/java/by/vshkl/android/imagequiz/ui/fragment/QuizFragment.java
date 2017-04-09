package by.vshkl.android.imagequiz.ui.fragment;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import by.vshkl.android.imagequiz.R;
import by.vshkl.android.imagequiz.mvp.model.QuizItem;
import by.vshkl.android.imagequiz.mvp.presenter.QuizPresenter;
import by.vshkl.android.imagequiz.mvp.view.QuizView;
import by.vshkl.android.imagequiz.ui.activity.MainActivity;
import by.vshkl.android.imagequiz.ui.listener.LifeRefillListener;
import by.vshkl.android.imagequiz.utils.AssetsUtils;
import by.vshkl.android.imagequiz.utils.DialogUtils;
import by.vshkl.android.imagequiz.utils.PrefUtils;

public class QuizFragment extends MvpAppCompatFragment implements QuizView, OnClickListener, LifeRefillListener {

    @InjectPresenter QuizPresenter presenter;

    private Toolbar tbToolbar;
    private TextView tvGuide;
    private GridLayout glImages;
    private ImageView ivPic1;
    private ImageView ivPic2;
    private ImageView ivPic3;
    private ImageView ivPic4;

    private MainActivity parentActivity;

    public static Fragment newInstance() {
        return new QuizFragment();
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
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tbToolbar = (Toolbar) view.findViewById(R.id.tb_toolbar);
        tvGuide = (TextView) view.findViewById(R.id.tv_guide);
        glImages = (GridLayout) view.findViewById(R.id.gl_images);
        ivPic1 = (ImageView) view.findViewById(R.id.iv_pic_1);
        ivPic2 = (ImageView) view.findViewById(R.id.iv_pic_2);
        ivPic3 = (ImageView) view.findViewById(R.id.iv_pic_3);
        ivPic4 = (ImageView) view.findViewById(R.id.iv_pic_4);

        ivPic1.setOnClickListener(this);
        ivPic2.setOnClickListener(this);
        ivPic3.setOnClickListener(this);
        ivPic4.setOnClickListener(this);

        parentActivity.setSupportActionBar(tbToolbar);

        presenter.beginQuiz(PrefUtils.getName(getContext()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_rating:
                parentActivity.getPresenter().showRating();
                return true;
            case R.id.action_change_player:
                presenter.changePlayer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        presenter.saveScore();
        presenter.onPause();
        super.onPause();
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
            case R.id.iv_pic_1:
                presenter.checkAnswer(1);
                break;
            case R.id.iv_pic_2:
                presenter.checkAnswer(2);
                break;
            case R.id.iv_pic_3:
                presenter.checkAnswer(3);
                break;
            case R.id.iv_pic_4:
                presenter.checkAnswer(4);
                break;
        }
    }

    @Override
    public void onConfirmLifeRefill() {
        presenter.doLifeRefill();
    }

    @Override
    public void showQuiz(QuizItem quizItem) {
        tvGuide.setText(R.string.quiz_message_guide);
        ivPic1.setImageBitmap(AssetsUtils.getBitmap(getContext(), quizItem.getPicNames()[0]));
        ivPic2.setImageBitmap(AssetsUtils.getBitmap(getContext(), quizItem.getPicNames()[1]));
        ivPic3.setImageBitmap(AssetsUtils.getBitmap(getContext(), quizItem.getPicNames()[2]));
        ivPic4.setImageBitmap(AssetsUtils.getBitmap(getContext(), quizItem.getPicNames()[3]));

        Point point = new Point();
        parentActivity.getWindowManager().getDefaultDisplay().getSize(point);
        int screenWidth = point.x;
        int screenHeight = point.y;

        int width;  //TODO: calculate real ratio
        if (screenWidth < screenHeight) {
            width = point.x * 233 / 300;
        } else {
            width = point.x * 300 / 233;
        }

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, width);
        lp.gravity = Gravity.CENTER;

        glImages.setLayoutParams(lp);
    }

    @Override
    public void showStats(int score, int life) {
        parentActivity.getSupportActionBar().setTitle(getString(R.string.title_score, score));
        parentActivity.getSupportActionBar().setSubtitle(getString(R.string.title_life, life));
    }

    @Override
    public void showIsCorrect(boolean isCorrect) {
        if (isCorrect) {
            presenter.nextQuiz();
        } else {
            tvGuide.setText(R.string.quiz_message_wrong);
        }
    }

    @Override
    public void changePlayer() {
        PrefUtils.setName(getContext(), "");
        PrefUtils.setLogged(getContext(), false);
        parentActivity.getPresenter().showInit(true);
    }

    @Override
    public void showLifeRefillDialog() {
        DialogUtils.showLifeRefillDialog(getContext(), this);
    }
}
