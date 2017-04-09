package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.R;
import by.vshkl.android.imagequiz.database.DatabaseRepository;
import by.vshkl.android.imagequiz.mvp.model.QuizItem;
import by.vshkl.android.imagequiz.mvp.model.Score;
import by.vshkl.android.imagequiz.mvp.view.QuizView;
import by.vshkl.android.imagequiz.utils.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@InjectViewState
public class QuizPresenter extends BasePresenter<QuizView> {

    private List<QuizItem> quizItems;
    private Score score;
    private int currentQuiz;

    public void onPause() {
        saveScore();
    }

    public void changePlayer() {
        getViewState().changePlayer();
    }

    public void doLifeRefill() {
        score.refillLife();
        startQuiz();
    }

    public void beginQuiz(String name) {
        setDisposable(DatabaseRepository.loadScore(name)
                .compose(RxUtils.<Score>applySchedulers())
                .subscribe(new Consumer<Score>() {
                    @Override
                    public void accept(@NonNull Score scoreResult) throws Exception {
                        score = scoreResult;
                        loadQuizItems();
                    }
                }));
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

    public void checkAnswer(int picId) {
        int picNumber = 0;

        switch (picId) {
            case R.id.iv_pic_1:
                picNumber = 1;
                break;
            case R.id.iv_pic_2:
                picNumber = 2;
                break;
            case R.id.iv_pic_3:
                picNumber = 3;
                break;
            case R.id.iv_pic_4:
                picNumber = 4;
                break;
        }

        if (quizItems.get(currentQuiz).getCorrect() == picNumber) {
            scoreUp();
            getViewState().showIsCorrect(true, picId);
        } else {
            scoreDown();
            getViewState().showIsCorrect(false, picId);
        }
        if (score.getLife() > 0) {
            getViewState().showStats(score.getScore(), score.getLife());
        } else {
            startQuiz();
        }
    }

    public void saveScore() {
        setDisposable(DatabaseRepository.saveScore(score)
                .compose(RxUtils.<Boolean>applySchedulers())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {

                    }
                }));
    }

    private void loadQuizItems() {
        setDisposable(DatabaseRepository.loadQuizItems()
                .compose(RxUtils.<List<QuizItem>>applySchedulers())
                .subscribe(new Consumer<List<QuizItem>>() {
                    @Override
                    public void accept(@NonNull List<QuizItem> quizItemsResult) throws Exception {
                        quizItems = quizItemsResult;
                        startQuiz();
                    }
                }));
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
}
