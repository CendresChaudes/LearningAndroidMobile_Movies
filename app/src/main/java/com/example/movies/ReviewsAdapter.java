package com.example.movies;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private List<Review> reviews = new ArrayList<>();

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );

        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = this.reviews.get(position);

        TextView textViewReviewAuthor = holder.getTextViewReviewAuthor();
        TextView textViewReviewText = holder.getTextViewReviewText();
        int colorId = this.getColorId(review.getType());
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorId);

        holder.itemView.setBackgroundColor(color);
        textViewReviewAuthor.setText(review.getAuthor());
        textViewReviewText.setText(review.getText());
    }

    @Override
    public int getItemCount() {
        return this.reviews.size();
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    public int getColorId(ReviewType type) {
        int id;

        switch (type) {
            case POSITIVE:
                id = R.color.green;
                break;
            case NEGATIVE:
                id = R.color.red;
                break;
            default:
                id = R.color.orange;
                break;
        }

        return id;
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewReviewAuthor;
        private TextView textViewReviewText;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            this.initViews();
        }

        public TextView getTextViewReviewAuthor() {
            return this.textViewReviewAuthor;
        }

        public TextView getTextViewReviewText() {
            return this.textViewReviewText;
        }

        private void initViews() {
            this.textViewReviewAuthor = itemView.findViewById(R.id.textViewReviewAuthor);
            this.textViewReviewText = itemView.findViewById(R.id.textViewReviewText);
        }
    }
}
