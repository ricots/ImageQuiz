package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import by.vshkl.android.imagequiz.database.DatabaseRepository;
import by.vshkl.android.imagequiz.mvp.model.Score;
import by.vshkl.android.imagequiz.mvp.view.RatingView;
import by.vshkl.android.imagequiz.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@InjectViewState
public class RatingPresenter extends BasePresenter<RatingView> {

    public void loadRating(final String name) {
        setDisposable(DatabaseRepository.loadScores()
                .compose(RxUtils.<List<Score>>applySchedulers())
                .subscribe(new Consumer<List<Score>>() {
                    @Override
                    public void accept(@NonNull List<Score> scores) throws Exception {
                        findPlayerScore(scores, name);
                    }
                }));
    }

    private void findPlayerScore(final List<Score> scores, final String name) {
        setDisposable(Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        for (int i = 0; i < scores.size(); i++) {
                            Score score = scores.get(i);
                            if (name.equals(score.getName())) {
                                score.setId(i + 1);
                                emitter.onNext(score.getName() + " - Номер " + (i + 1));
                            }
                        }
                    }
                })
                .compose(RxUtils.<String>applySchedulers())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String string) throws Exception {
                        getViewState().showRating(scores, string);
                    }
                }));
    }
}
