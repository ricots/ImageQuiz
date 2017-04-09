package by.vshkl.android.imagequiz.mvp.model;

import by.vshkl.android.imagequiz.constants.GameState;

public class Score {

    private long id;
    String name;
    int score;
    int life;

    public Score() {
    }

    public Score(String name) {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.score = 0;
        this.life = 10;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void scoreUp() {
        score += 150;
    }

    public
    @GameState
    int scoreDown() {
        if (score == 0) {
            return GameState.NEGATIVE_SCORE;
        } else if (life == 1) {
            life--;
            return GameState.ZERO_LIFE;
        } else {
            score -= 50;
            life--;
            return GameState.OK;
        }
    }

    public void refillLife(int amount) {
        life = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Score)) {
            return false;
        }

        Score score = (Score) o;

        return getId() == score.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Score{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", score=").append(score);
        sb.append(", life=").append(life);
        sb.append('}');
        return sb.toString();
    }
}
