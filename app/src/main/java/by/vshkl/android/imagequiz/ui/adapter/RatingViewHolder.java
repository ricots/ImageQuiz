package by.vshkl.android.imagequiz.ui.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import by.vshkl.android.imagequiz.R;

class RatingViewHolder extends ViewHolder {

    TextView tvRatingNumber;
    TextView tvRatingName;
    TextView tvRatingScore;

    RatingViewHolder(View itemView) {
        super(itemView);

        tvRatingNumber = (TextView) itemView.findViewById(R.id.tv_rating_number);
        tvRatingName = (TextView) itemView.findViewById(R.id.tv_rating_name);
        tvRatingScore = (TextView) itemView.findViewById(R.id.tv_rating_score);

    }
}
