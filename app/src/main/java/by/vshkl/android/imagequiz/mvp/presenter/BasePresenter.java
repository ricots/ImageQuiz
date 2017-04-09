package by.vshkl.android.imagequiz.mvp.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.Disposable;

class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

    private Disposable disposable;

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }
}
