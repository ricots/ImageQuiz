package by.vshkl.android.imagequiz.database.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.database.entity.ScoreEntity;
import by.vshkl.android.imagequiz.mvp.model.Score;

public class ScoreEntityMapper {

    public static Score transform(ScoreEntity scoreEntity) {
        Score score = null;

        if (scoreEntity != null) {
            score = new Score();
            score.setId(scoreEntity.getLife());
            score.setName(scoreEntity.getName());
            score.setScore(scoreEntity.getScore());
            score.setLife(scoreEntity.getLife());
        }

        return score;
    }

    public static List<Score> transform(List<ScoreEntity> scoreEntities) {
        if (scoreEntities == null || scoreEntities.isEmpty()) {
            return Collections.emptyList();
        }

        List<Score> scores = new ArrayList<>(scoreEntities.size());
        for (ScoreEntity scoreEntity : scoreEntities) {
            if (scoreEntity != null) {
                scores.add(transform(scoreEntity));
            }
        }

        return scores;
    }
}
