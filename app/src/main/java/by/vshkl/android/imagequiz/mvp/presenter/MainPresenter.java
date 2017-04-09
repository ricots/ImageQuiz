package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import by.vshkl.android.imagequiz.mvp.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public void showQuiz() {
        getViewState().showQuiz();
    }

    public void showRating() {
        getViewState().showRating();
    }
}
