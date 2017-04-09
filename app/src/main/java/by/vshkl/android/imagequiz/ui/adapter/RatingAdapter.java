package by.vshkl.android.imagequiz.ui.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import by.vshkl.android.imagequiz.R;
import by.vshkl.android.imagequiz.mvp.model.Score;

public class RatingAdapter extends Adapter<RatingViewHolder> {

    private List<Score> scores;

    @Override
    public RatingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RatingViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rating, parent, false));
    }

    @Override
    public void onBindViewHolder(RatingViewHolder holder, int position) {
        final Score score = scores.get(position);

        holder.tvRatingNumber.setText(String.valueOf(position + 1));
        holder.tvRatingName.setText(score.getName());
        holder.tvRatingScore.setText(String.valueOf(score.getScore()));
    }

    @Override
    public int getItemCount() {
        return scores != null ? scores.size() : 0;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
