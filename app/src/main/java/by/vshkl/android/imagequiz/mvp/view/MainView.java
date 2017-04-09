package by.vshkl.android.imagequiz.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void checkLogged();

    void showInit();

    void showQuiz();

    void showRating();
}
