package by.vshkl.android.imagequiz.database.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.database.entity.ScoreEntity;
import by.vshkl.android.imagequiz.mvp.model.Score;

public class ScoreMapper {

    public static ScoreEntity transform(Score score) {
        ScoreEntity scoreEntity = null;

        if (score != null) {
            scoreEntity = new ScoreEntity();
            scoreEntity.setId(score.getId());
            scoreEntity.setName(score.getName());
            scoreEntity.setScore(score.getScore());
            scoreEntity.setLife(score.getLife());
        }

        return scoreEntity;
    }

    public static List<ScoreEntity> transform(List<Score> scores) {
        if (scores == null || scores.isEmpty()) {
            return Collections.emptyList();
        }

        List<ScoreEntity> scoreEntities = new ArrayList<>(scores.size());
        for (Score score : scores) {
            if (score != null) {
                scoreEntities.add(transform(score));
            }
        }

        return scoreEntities;
    }
}
