package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import by.vshkl.android.imagequiz.mvp.view.MainView;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Disposable disposable;

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void checkLogged() {
        getViewState().checkLogged();
    }

    public void showInit() {
        getViewState().showInit();
    }

    public void showQuiz() {
        getViewState().showQuiz();
    }

    public void showRating() {
        getViewState().showRating();
    }
}
