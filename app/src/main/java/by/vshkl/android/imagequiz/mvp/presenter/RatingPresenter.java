package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import by.vshkl.android.imagequiz.database.DatabaseRepository;
import by.vshkl.android.imagequiz.mvp.model.Score;
import by.vshkl.android.imagequiz.mvp.view.RatingView;
import by.vshkl.android.imagequiz.utils.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@InjectViewState
public class RatingPresenter extends BasePresenter<RatingView> {

    public void loadRating() {
        setDisposable(DatabaseRepository.loadScores()
                .compose(RxUtils.<List<Score>>applySchedulers())
                .subscribe(new Consumer<List<Score>>() {
                    @Override
                    public void accept(@NonNull List<Score> scores) throws Exception {
                        getViewState().showRating(scores);
                    }
                }));
    }
}
