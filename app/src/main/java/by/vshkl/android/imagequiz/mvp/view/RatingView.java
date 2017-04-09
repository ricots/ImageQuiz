package by.vshkl.android.imagequiz.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import by.vshkl.android.imagequiz.mvp.model.Score;

public interface RatingView extends MvpView {

    void showRating(List<Score> scores, String scoreTitle);
}
