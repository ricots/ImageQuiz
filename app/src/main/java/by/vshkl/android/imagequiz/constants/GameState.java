package by.vshkl.android.imagequiz.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({GameState.ZERO_LIFE, GameState.NEGATIVE_SCORE, GameState.OK})
@Retention(RetentionPolicy.SOURCE)
public @interface GameState {
    int ZERO_LIFE = 0;
    int NEGATIVE_SCORE = 1;
    int OK = 2;
}
