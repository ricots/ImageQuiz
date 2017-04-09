package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import by.vshkl.android.imagequiz.database.DatabaseRepository;
import by.vshkl.android.imagequiz.mvp.model.Score;
import by.vshkl.android.imagequiz.mvp.view.MainView;
import by.vshkl.android.imagequiz.network.NetworkRepository;
import by.vshkl.android.imagequiz.utils.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    public void updateScores() {
        setDisposable(NetworkRepository.getScores()
                .compose(RxUtils.<List<Score>>applySchedulers())
                .subscribe(new Consumer<List<Score>>() {
                    @Override
                    public void accept(@NonNull List<Score> scores) throws Exception {
                        updateScores(scores);
                    }
                }));
    }

    public void checkLogged() {
        getViewState().checkLogged();
    }

    public void showInit(boolean isChangePlayer) {
        getViewState().showInit(isChangePlayer);
    }

    public void showQuiz() {
        updateScores();
    }

    public void showRating() {
        getViewState().showRating();
    }

    private void updateScores(List<Score> scores) {
        setDisposable(DatabaseRepository.saveScores(scores)
                .compose(RxUtils.<Boolean>applySchedulers())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        getViewState().showQuiz();
                    }
                }));
    }
}
