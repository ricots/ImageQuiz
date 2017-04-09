package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import by.vshkl.android.imagequiz.database.DatabaseRepository;
import by.vshkl.android.imagequiz.mvp.view.InitView;
import by.vshkl.android.imagequiz.network.NetworkRepository;
import by.vshkl.android.imagequiz.network.Quiz;
import by.vshkl.android.imagequiz.utils.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

@InjectViewState
public class InitPresenter extends MvpPresenter<InitView> {

    private Disposable disposable;

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void downloadQuiz() {
        getViewState().showProgress();
        disposable = NetworkRepository.getQuizConfig()
                .compose(RxUtils.<List<Quiz>>applySchedulers())
                .subscribe(new Consumer<List<Quiz>>() {
                    @Override
                    public void accept(@NonNull List<Quiz> quizs) throws Exception {
                        disposable = DatabaseRepository.saveQuiz(quizs)
                                .compose(RxUtils.<Boolean>applySchedulers())
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                                        getViewState().showStart();
                                    }
                                });
                    }
                });
    }

    public void startQuiz() {
        getViewState().showQuiz();
    }
}
