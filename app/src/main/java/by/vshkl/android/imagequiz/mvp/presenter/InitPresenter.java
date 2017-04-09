package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import by.vshkl.android.imagequiz.mvp.view.InitView;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class InitPresenter extends MvpPresenter<InitView> {

    private Disposable disposable;

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
