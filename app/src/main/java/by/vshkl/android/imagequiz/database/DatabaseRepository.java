package by.vshkl.android.imagequiz.database;

import java.util.List;

import by.vshkl.android.imagequiz.database.entity.QuizItemEntity;
import by.vshkl.android.imagequiz.network.Quiz;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class DatabaseRepository {

    public static Observable<Boolean> saveQuiz(final List<Quiz> quizs) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                boolean result = true;
                for (Quiz quiz : quizs) {
                    QuizItemEntity quizItemEntity = new QuizItemEntity();
                    quizItemEntity.setId(quiz.getId());
                    quizItemEntity.setCorrect(quiz.getCorrect());
                    quizItemEntity.setCorrectDescription(quiz.getCorrectDescription());
                    result = result && quizItemEntity.save();
                }
                emitter.onNext(result);
            }
        });
    }
}
