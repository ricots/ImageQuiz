package by.vshkl.android.imagequiz.database.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

import by.vshkl.android.imagequiz.database.AppDatabase;

@Table(database = AppDatabase.class, name = "Scores")
public class ScoreEntity extends BaseModel {

    @PrimaryKey private long id;
    @Column @Unique(onUniqueConflict = ConflictAction.REPLACE) String name;
    @Column int score;
    @Column int life;

    public ScoreEntity() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScoreEntity)) {
            return false;
        }

        ScoreEntity that = (ScoreEntity) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScoreEntity{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", score=").append(score);
        sb.append(", life=").append(life);
        sb.append('}');
        return sb.toString();
    }
}
