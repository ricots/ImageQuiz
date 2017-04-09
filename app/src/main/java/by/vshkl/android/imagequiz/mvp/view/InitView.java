package by.vshkl.android.imagequiz.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface InitView extends MvpView {

    void showEmpty();

    void showProgress();

    void showStart();

    void showQuiz();

    void quizDownloaded();
}
