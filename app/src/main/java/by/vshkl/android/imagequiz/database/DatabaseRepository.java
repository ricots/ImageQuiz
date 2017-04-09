package by.vshkl.android.imagequiz.database;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.database.entity.QuizItemEntity;
import by.vshkl.android.imagequiz.database.entity.ScoreEntity;
import by.vshkl.android.imagequiz.database.entity.ScoreEntity_Table;
import by.vshkl.android.imagequiz.database.mapper.QuizMapper;
import by.vshkl.android.imagequiz.database.mapper.ScoreMapper;
import by.vshkl.android.imagequiz.mvp.mapper.QuizItemEntityMapper;
import by.vshkl.android.imagequiz.mvp.mapper.ScoreEntityMapper;
import by.vshkl.android.imagequiz.mvp.model.QuizItem;
import by.vshkl.android.imagequiz.mvp.model.Score;
import by.vshkl.android.imagequiz.network.model.Quiz;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class DatabaseRepository {

    public static Observable<Boolean> saveQuiz(final List<Quiz> quizs) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> emitter) throws Exception {
                FlowManager.getDatabase(AppDatabase.class).beginTransactionAsync(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        for (Quiz quiz : quizs) {
                            QuizMapper.transform(quiz).save(databaseWrapper);
                        }
                    }
                }).success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        emitter.onNext(true);
                    }
                }).error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        emitter.onNext(false);
                    }
                }).build().execute();
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

    public static Observable<Boolean> saveScores(final List<Score> scores) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> emitter) throws Exception {
                FlowManager.getDatabase(AppDatabase.class).beginTransactionAsync(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        for (Score score : scores) {
                            ScoreMapper.transform(score).save(databaseWrapper);
                        }
                    }
                }).success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        emitter.onNext(true);
                    }
                }).error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        emitter.onNext(false);
                    }
                }).build().execute();
            }
        });
    }

    public static Observable<List<Score>> loadScores() {
        return Observable.create(new ObservableOnSubscribe<List<Score>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Score>> emitter) throws Exception {
                List<Score> scores = ScoreEntityMapper.transform(SQLite.select().from(ScoreEntity.class)
                        .orderBy(OrderBy.fromProperty(ScoreEntity_Table.score)).limit(30).queryList());
                emitter.onNext(scores != null ? scores : Collections.<Score>emptyList());
            }
        });
    }
}
