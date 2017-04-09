package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import by.vshkl.android.imagequiz.mvp.view.MainView;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    public void checkLogged() {
        getViewState().checkLogged();
    }

    public void showInit(boolean isChangePlayer) {
        getViewState().showInit(isChangePlayer);
    }

    public void showQuiz() {
        getViewState().showQuiz();
    }

    public void showRating() {
        getViewState().showRating();
    }

}
