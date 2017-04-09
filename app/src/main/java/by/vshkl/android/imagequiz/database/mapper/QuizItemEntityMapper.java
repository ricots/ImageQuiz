package by.vshkl.android.imagequiz.database.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.database.entity.QuizItemEntity;
import by.vshkl.android.imagequiz.mvp.model.QuizItem;

public class QuizItemEntityMapper {

    public static QuizItem transform(QuizItemEntity quizItemEntity) {
        QuizItem quizItem = null;

        if (quizItemEntity != null) {
            quizItem = new QuizItem();
            quizItem.setId(quizItemEntity.getId());
            quizItem.setPicNames(quizItemEntity.getPicNames());
            quizItem.setCorrect(quizItemEntity.getCorrect());
            quizItem.setCorrectDescription(quizItemEntity.getCorrectDescription());
        }

        return quizItem;
    }

    public static List<QuizItem> transform(List<QuizItemEntity> quizItemEntities) {
        if (quizItemEntities == null || quizItemEntities.isEmpty()) {
            return Collections.emptyList();
        }

        List<QuizItem> quizItems = new ArrayList<>(quizItemEntities.size());
        for (QuizItemEntity quizItemEntity : quizItemEntities) {
            if (quizItemEntity != null) {
                quizItems.add(transform(quizItemEntity));
            }
        }

        return quizItems;
    }
}
