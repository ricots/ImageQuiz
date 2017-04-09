package by.vshkl.android.imagequiz.ui.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import by.vshkl.android.imagequiz.R;
import by.vshkl.android.imagequiz.ui.view.RobotoMediumTextView;
import by.vshkl.android.imagequiz.ui.view.RobotoRegularTextView;

class RatingViewHolder extends ViewHolder {

    RobotoMediumTextView tvRatingNumber;
    RobotoRegularTextView tvRatingName;
    RobotoMediumTextView tvRatingScore;

    RatingViewHolder(View itemView) {
        super(itemView);

        tvRatingNumber = (RobotoMediumTextView) itemView.findViewById(R.id.tv_rating_number);
        tvRatingName = (RobotoRegularTextView) itemView.findViewById(R.id.tv_rating_name);
        tvRatingScore = (RobotoMediumTextView) itemView.findViewById(R.id.tv_rating_score);

    }
}
