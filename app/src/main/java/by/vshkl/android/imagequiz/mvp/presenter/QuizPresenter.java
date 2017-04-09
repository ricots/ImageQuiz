package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import by.vshkl.android.imagequiz.mvp.model.QuizItem;
import by.vshkl.android.imagequiz.mvp.view.QuizView;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class QuizPresenter extends MvpPresenter<QuizView> {

    private Disposable disposable;
    private List<QuizItem> quizItems;
    private int currentQuiz;
    private int life;
    private int score;

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void showQuizPics() {
        getViewState().showQuizPics();
    }
}
