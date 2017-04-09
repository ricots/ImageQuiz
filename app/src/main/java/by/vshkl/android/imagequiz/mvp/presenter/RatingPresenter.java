package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import by.vshkl.android.imagequiz.mvp.view.RatingView;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class RatingPresenter extends MvpPresenter<RatingView> {

    private Disposable disposable;

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
