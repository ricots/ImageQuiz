package by.vshkl.android.imagequiz.database.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import by.vshkl.android.imagequiz.database.AppDatabase;

@Table(database = AppDatabase.class, name = "QuizItems")
public class QuizItemEntity extends BaseModel {

    @PrimaryKey private int id;
    @Column private int correct;
    @Column private String correctDescription;

    public QuizItemEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public String getCorrectDescription() {
        return correctDescription;
    }

    public void setCorrectDescription(String correctDescription) {
        this.correctDescription = correctDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuizItemEntity)) {
            return false;
        }

        QuizItemEntity that = (QuizItemEntity) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuizItemEntity{");
        sb.append("id=").append(id);
        sb.append(", correct=").append(correct);
        sb.append(", correctDescription='").append(correctDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
