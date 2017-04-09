package by.vshkl.android.imagequiz.network;

public class Quiz {

    private int id;
    private int correct;
    private String correctDescription;

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
        if (!(o instanceof Quiz)) {
            return false;
        }

        Quiz quiz = (Quiz) o;

        return getId() == quiz.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Quiz{");
        sb.append("id=").append(id);
        sb.append(", correct=").append(correct);
        sb.append(", correctDescription='").append(correctDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
