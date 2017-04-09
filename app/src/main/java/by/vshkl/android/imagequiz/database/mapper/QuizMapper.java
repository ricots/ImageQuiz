package by.vshkl.android.imagequiz.database.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.database.entity.QuizItemEntity;
import by.vshkl.android.imagequiz.network.model.Quiz;

public class QuizMapper {

    public static QuizItemEntity transform(Quiz quiz) {
        QuizItemEntity quizItemEntity = null;

        if (quiz != null) {
            quizItemEntity = new QuizItemEntity();
            quizItemEntity.setId(quiz.getId());
            quizItemEntity.setCorrect(quiz.getCorrect());
            quizItemEntity.setCorrectDescription(quiz.getCorrectDescription());
        }

        return quizItemEntity;
    }

    public static List<QuizItemEntity> transform(List<Quiz> quizs) {
        if (quizs == null || quizs.isEmpty()) {
            return Collections.emptyList();
        }

        List<QuizItemEntity> scoreEntities = new ArrayList<>(quizs.size());
        for (Quiz quiz : quizs) {
            if (quiz != null) {
                scoreEntities.add(transform(quiz));
            }
        }

        return scoreEntities;
    }
}
