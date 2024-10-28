package com.example.movies.screens.moviedetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.common.Cinema;

import java.util.ArrayList;
import java.util.List;

public class CinemasAdapter extends RecyclerView.Adapter<CinemasAdapter.CinemaViewHolder> {

    private List<Cinema> cinemas = new ArrayList<>();

    private OnCinemaLinkItemClickListener onCinemaLinkItemClickListener;

    @NonNull
    @Override
    public CinemaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cinema_link_item,
                parent,
                false
        );

        return new CinemaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaViewHolder holder, int position) {
        Cinema cinema = this.cinemas.get(position);

        TextView textViewTitle = holder.getTextViewTitle();
        textViewTitle.setText(cinema.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCinemaLinkItemClickListener != null) {
                    onCinemaLinkItemClickListener.onClick(cinema);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.cinemas.size();
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
        notifyDataSetChanged();
    }

    public void setOnCinemaLinkItemClickListener(
            OnCinemaLinkItemClickListener onCinemaLinkItemClickListener
    ) {
        this.onCinemaLinkItemClickListener = onCinemaLinkItemClickListener;
    }

    interface OnCinemaLinkItemClickListener {

        void onClick(Cinema cinema);
    }

    public static class CinemaViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;

        public CinemaViewHolder(@NonNull View itemView) {
            super(itemView);
            this.initViews();
        }

        public TextView getTextViewTitle() {
            return this.textViewTitle;
        }

        private void initViews() {
            this.textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
