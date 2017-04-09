package by.vshkl.android.imagequiz.database;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.database.entity.QuizItemEntity;
import by.vshkl.android.imagequiz.database.entity.ScoreEntity;
import by.vshkl.android.imagequiz.database.entity.ScoreEntity_Table;
import by.vshkl.android.imagequiz.database.mapper.QuizItemEntityMapper;
import by.vshkl.android.imagequiz.database.mapper.ScoreEntityMapper;
import by.vshkl.android.imagequiz.database.mapper.ScoreMapper;
import by.vshkl.android.imagequiz.mvp.model.QuizItem;
import by.vshkl.android.imagequiz.mvp.model.Score;
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

    public static Observable<List<QuizItem>> loadQuizItems() {
        return Observable.create(new ObservableOnSubscribe<List<QuizItem>>() {
            @Override
            public void subscribe(ObservableEmitter<List<QuizItem>> emitter) throws Exception {
                List<QuizItem> quizItems = QuizItemEntityMapper.transform(
                        SQLite.select().from(QuizItemEntity.class).queryList());
                emitter.onNext(quizItems != null ? quizItems : Collections.<QuizItem>emptyList());
            }
        });
    }

    public static Observable<Boolean> saveScore(final Score score) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(ScoreMapper.transform(score).save());
            }
        });
    }

    public static Observable<Score> loadScore(final String name) {
        return Observable.create(new ObservableOnSubscribe<Score>() {
            @Override
            public void subscribe(ObservableEmitter<Score> emitter) throws Exception {
                Score score = ScoreEntityMapper.transform(SQLite.select().from(ScoreEntity.class)
                        .where(ScoreEntity_Table.name.eq(name)).querySingle());
                emitter.onNext(score != null ? score : new Score(name));
            }
        });
    }
}
