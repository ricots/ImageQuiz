package by.vshkl.android.imagequiz.database.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.database.entity.QuizItemEntity;
import by.vshkl.android.imagequiz.mvp.model.QuizItem;

public class QuizItemEntityMapper {

    public static QuizItem transform(QuizItemEntity quizItemEntity, String[] pics) {
        QuizItem quizItem = null;

        if (quizItemEntity != null) {
            quizItem = new QuizItem();
            quizItem.setId(quizItemEntity.getId());
            quizItem.setPicNames(pics);
            quizItem.setCorrect(quizItemEntity.getCorrect());
            quizItem.setCorrectDescription(quizItemEntity.getCorrectDescription());
        }

        return quizItem;
    }

    public static List<QuizItem> transform(List<QuizItemEntity> quizItemEntities, List<String[]> pics) {
        if (quizItemEntities == null || quizItemEntities.isEmpty()) {
            return Collections.emptyList();
        }

        List<QuizItem> quizItems = new ArrayList<>(quizItemEntities.size());
        for (int i = 0; i < quizItemEntities.size(); i++) {
            QuizItemEntity quizItemEntity = quizItemEntities.get(i);
            String[] picsArray = pics.get(i);
            if (quizItemEntity != null && picsArray != null) {
                quizItems.add(transform(quizItemEntity, picsArray));
            }
        }

        return quizItems;
    }
}
