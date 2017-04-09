package by.vshkl.android.imagequiz.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface QuizView extends MvpView {

    void showQuizPics();

    void showStats(int score, int life);
}
