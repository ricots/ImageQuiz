package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import by.vshkl.android.imagequiz.database.DatabaseRepository;
import by.vshkl.android.imagequiz.mvp.view.InitView;
import by.vshkl.android.imagequiz.network.NetworkRepository;
import by.vshkl.android.imagequiz.network.model.Quiz;
import by.vshkl.android.imagequiz.utils.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@InjectViewState
public class InitPresenter extends BasePresenter<InitView> {

    public void showEmpty() {
        getViewState().showEmpty();
    }

    public void showStart() {
        getViewState().showStart();
    }

    public void downloadQuiz() {
        getViewState().showProgress();
        setDisposable(NetworkRepository.getQuizConfig()
                .compose(RxUtils.<List<Quiz>>applySchedulers())
                .subscribe(new Consumer<List<Quiz>>() {
                    @Override
                    public void accept(@NonNull List<Quiz> quizs) throws Exception {
                        saveQuizItems(quizs);
                    }
                }));
    }

    public void startQuiz() {
        getViewState().showQuiz();
    }

    private void saveQuizItems(List<Quiz> quizs) {
        setDisposable(DatabaseRepository.saveQuiz(quizs)
                .compose(RxUtils.<Boolean>applySchedulers())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        getViewState().quizDownloaded();
                        getViewState().showStart();
                    }
                }));
    }
}
