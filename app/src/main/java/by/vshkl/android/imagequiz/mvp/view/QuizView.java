package by.vshkl.android.imagequiz.mvp.view;

import com.arellomobile.mvp.MvpView;

import by.vshkl.android.imagequiz.mvp.model.QuizItem;

public interface QuizView extends MvpView {

    void showQuiz(QuizItem quizItem);

    void showStats(int score, int life);

    void showIsCorrect(boolean isCorrect);

    void changePlayer();
}
