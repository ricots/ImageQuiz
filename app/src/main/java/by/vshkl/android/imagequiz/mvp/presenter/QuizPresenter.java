package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.constants.GameState;
import by.vshkl.android.imagequiz.database.DatabaseRepository;
import by.vshkl.android.imagequiz.mvp.model.QuizItem;
import by.vshkl.android.imagequiz.mvp.model.Score;
import by.vshkl.android.imagequiz.mvp.view.QuizView;
import by.vshkl.android.imagequiz.utils.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

@InjectViewState
public class QuizPresenter extends MvpPresenter<QuizView> {

    private Disposable disposable;
    private List<QuizItem> quizItems;
    private Score score;
    private int currentQuiz;
    private int life;
    private int scoreN;

    public void onPause() {
        saveScore();
    }

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void changePlayer() {
        getViewState().changePlayer();
    }

    public void doLifeRefill() {
        score.refillLife();
        startQuiz();
    }

    public void beginQuiz(String name) {
        disposable = DatabaseRepository.loadScore(name)
                .compose(RxUtils.<Score>applySchedulers())
                .subscribe(new Consumer<Score>() {
                    @Override
                    public void accept(@NonNull Score scoreResult) throws Exception {
                        score = scoreResult;
                        loadQuizItems();
                    }
                });
    }

    public void nextQuiz() {
        if (score.getLife() <= 0) {
            getViewState().showLifeRefillDialog();
            return;
        }
        currentQuiz++;
        if (currentQuiz == 20) {
            startQuiz();
        }
        getViewState().showQuiz(quizItems.get(currentQuiz));
    }

    public void checkAnswer(int picNumber) {
        if (quizItems.get(currentQuiz).getCorrect() == picNumber) {
            scoreUp();
            getViewState().showIsCorrect(true);
        } else {
            scoreDown();
            getViewState().showIsCorrect(false);
        }
        if (score.getLife() > 0) {
            getViewState().showStats(score.getScore(), score.getLife());
        } else {
            startQuiz();
        }
    }

    private void loadQuizItems() {
        disposable = DatabaseRepository.loadQuizItems()
                .compose(RxUtils.<List<QuizItem>>applySchedulers())
                .subscribe(new Consumer<List<QuizItem>>() {
                    @Override
                    public void accept(@NonNull List<QuizItem> quizItemsResult) throws Exception {
                        quizItems = quizItemsResult;
                        startQuiz();
                    }
                });
    }

    private void startQuiz() {
        Collections.shuffle(quizItems);
        currentQuiz = 0;
        getViewState().showStats(score.getScore(), score.getLife());
        nextQuiz();
    }

    private void scoreDown() {
        if (score.getLife() > 0) {
            score.scoreDown();
        }
    }

    private void scoreUp() {
        score.scoreUp();
    }

    private void saveScore() {
        disposable = DatabaseRepository.saveScore(score)
                .compose(RxUtils.<Boolean>applySchedulers())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {

                    }
                });
    }
}
