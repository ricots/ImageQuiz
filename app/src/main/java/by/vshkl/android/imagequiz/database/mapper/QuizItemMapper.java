package by.vshkl.android.imagequiz.database.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.database.entity.QuizItemEntity;
import by.vshkl.android.imagequiz.mvp.model.QuizItem;

public class QuizItemMapper {

    public static QuizItemEntity transform(QuizItem quizItem) {
        QuizItemEntity quizItemEntity = null;

        if (quizItem != null) {
            quizItemEntity = new QuizItemEntity();
            quizItemEntity.setId(quizItem.getId());
            quizItemEntity.setPicNames(quizItem.getPicNames());
            quizItemEntity.setCorrect(quizItem.getCorrect());
            quizItemEntity.setCorrectDescription(quizItem.getCorrectDescription());
        }

        return quizItemEntity;
    }

    public static List<QuizItemEntity> transform(List<QuizItem> quizItems) {
        if (quizItems == null || quizItems.isEmpty()) {
            return Collections.emptyList();
        }

        List<QuizItemEntity> quizItemEntities = new ArrayList<>(quizItems.size());
        for (QuizItem quizItem : quizItems) {
            if (quizItem != null) {
                quizItemEntities.add(transform(quizItem));
            }
        }

        return quizItemEntities;
    }
}
