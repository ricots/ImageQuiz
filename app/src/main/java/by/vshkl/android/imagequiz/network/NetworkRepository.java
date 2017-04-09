package by.vshkl.android.imagequiz.network;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.vshkl.android.imagequiz.mvp.model.Score;
import by.vshkl.android.imagequiz.network.model.Quiz;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class NetworkRepository {

    public static Observable<List<Quiz>> getQuizConfig() {
        return Observable.create(new ObservableOnSubscribe<List<Quiz>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<Quiz>> emitter) throws Exception {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference configRef = database.getReference("quiz");

                configRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Quiz> quizs = new ArrayList<>();
                        for (DataSnapshot quizSnapshot : dataSnapshot.getChildren()) {
                            quizs.add(quizSnapshot.getValue(Quiz.class));
                        }
                        emitter.onNext(quizs);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public static Observable<List<Score>> getScores() {
        return Observable.create(new ObservableOnSubscribe<List<Score>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<Score>> emitter) throws Exception {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference configRef = database.getReference("score");

                configRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Score> scores = new ArrayList<>();
                        for (DataSnapshot quizSnapshot : dataSnapshot.getChildren()) {
                            scores.add(quizSnapshot.getValue(Score.class));
                        }
                        emitter.onNext(scores);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public static Observable<Boolean> saveScore(final Score score) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> emitter) throws Exception {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference scorePlayerRef = database
                        .getReference("score").child(String.valueOf(score.getId()));

                final Map<String, Score> scoreMap = new HashMap<>();
                scoreMap.put(String.valueOf(score.getId()), score);

                scorePlayerRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            Score remoteScore = dataSnapshot.getValue(Score.class);
                            if (remoteScore.getScore() <= score.getScore()) {
                                scorePlayerRef.setValue(score);
                            }
                        } else {
                            scorePlayerRef.setValue(score);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
