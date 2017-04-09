package by.vshkl.android.imagequiz.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.ads.MobileAds;

import by.vshkl.android.imagequiz.R;
import by.vshkl.android.imagequiz.mvp.presenter.MainPresenter;
import by.vshkl.android.imagequiz.mvp.view.MainView;
import by.vshkl.android.imagequiz.utils.Navigator;
import by.vshkl.android.imagequiz.utils.PrefUtils;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter MainPresenter presenter;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), getString(R.string.ad_app_id));

        presenter.checkLogged();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    public void checkLogged() {
        if (!PrefUtils.getDownloaded(getApplicationContext())) {
            presenter.showInit(false);
        } else if (!PrefUtils.getLogged(getApplicationContext())) {
            presenter.showInit(true);
        } else {
            presenter.showQuiz();
        }
    }

    @Override
    public void showInit(boolean isChangePlayer) {
        Navigator.showInit(MainActivity.this, isChangePlayer);
    }

    @Override
    public void showQuiz() {
        Navigator.showQuiz(MainActivity.this);
    }

    @Override
    public void showRating() {
        Navigator.showRating(MainActivity.this);
    }

    public MainPresenter getPresenter() {
        return presenter;
    }
}
