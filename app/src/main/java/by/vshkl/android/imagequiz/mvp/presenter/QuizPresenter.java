package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import by.vshkl.android.imagequiz.mvp.view.QuizView;

@InjectViewState
public class QuizPresenter extends MvpPresenter<QuizView> {

    public void showQuizPics() {
        getViewState().showQuizPics();
    }
}
