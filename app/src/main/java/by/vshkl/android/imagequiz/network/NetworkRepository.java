package by.vshkl.android.imagequiz.network;

import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class NetworkRepository {

    private static final String URL = "gs://imagequiz-b4b23.appspot.com/";

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

    public static Observable<List<String>> downloadQuiz(final int maxPicId) {
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                FirebaseStorage storage = FirebaseStorage.getInstance(URL);

                final List<String> picUrls = new ArrayList<>();
                for (int i = 1; i <= maxPicId; i++) {
                    for (int j = 1; j <= 4; j++) {
                        String resStr = "pics/" + i + "." + j + ".jpg";
                        StorageReference picsRef = storage.getReference().child(resStr);
                        picsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                picUrls.add(uri.toString());
                            }
                        });
                    }
                }

                emitter.onNext(picUrls);
            }
        });
    }
}
