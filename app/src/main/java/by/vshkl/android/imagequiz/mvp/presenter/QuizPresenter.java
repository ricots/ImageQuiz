package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.database.DatabaseRepository;
import by.vshkl.android.imagequiz.mvp.model.QuizItem;
import by.vshkl.android.imagequiz.mvp.view.QuizView;
import by.vshkl.android.imagequiz.utils.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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

    public void loadQuizItems() {
        disposable = DatabaseRepository.loadQuizItems()
                .compose(RxUtils.<List<QuizItem>>applySchedulers())
                .subscribe(new Consumer<List<QuizItem>>() {
                    @Override
                    public void accept(@NonNull List<QuizItem> quizItemsList) throws Exception {
                        quizItems = quizItemsList;
                        startQuizClean();
                    }
                });
    }

    public void nextQuiz() {
        currentQuiz++;
        if (currentQuiz == 20) {
            startQuiz();
        }
        getViewState().showQuiz(quizItems.get(currentQuiz));
    }

    public void checkAnswer(int picNumber) {
        if (quizItems.get(currentQuiz).getCorrect() == picNumber) {
            getViewState().showIsCorrect(true);
            scoreUp();
        } else {
            getViewState().showIsCorrect(false);
            scoreDown();
        }
        if (life > 0) {
            getViewState().showStats(life, score);
        } else {
            startQuizClean();
        }
    }

    private void startQuizClean() {
        life = 10;  //TODO: Replace with database value for current player
        score = 0;  //TODO: Replace with database value for current player
        startQuiz();
    }

    private void startQuiz() {
        Collections.shuffle(quizItems);
        currentQuiz = 0;
        nextQuiz();
    }

    private void scoreDown() {
        if (score > 0) {
            score -= 50;
        }
        life--;
    }

    private void scoreUp() {
        score += 150;
    }
}
