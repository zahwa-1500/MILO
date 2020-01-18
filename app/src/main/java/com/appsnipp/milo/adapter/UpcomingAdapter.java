package com.appsnipp.milo.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.milo.R;
import com.appsnipp.milo.detail.DetailUpcoming;
import com.appsnipp.milo.model.Upcoming;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.MovieViewHolder> {

    private ArrayList<Upcoming> mData = new ArrayList<>();
    public void setData(ArrayList<Upcoming> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_upcoming, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView tv_Image;
        TextView tvTitle, tvDate,
                tvRating;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_tanggal_rilis);
            tvRating = itemView.findViewById(R.id.tv_rate);
            tv_Image = itemView.findViewById(R.id.photo);
            itemView.setOnClickListener(this);
        }
        void bind(Upcoming upcoming) {
            String vote_average = Double.toString(upcoming.getRate());
            String url_image = "https://image.tmdb.org/t/p/w185" + upcoming.getPhoto();

            tvTitle.setText(upcoming.getTitle());
            tvDate.setText(upcoming.getRelease_date());
            tvRating.setText(vote_average);

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(tv_Image);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Upcoming upcoming = mData.get(position);
//
            upcoming.setTitle(upcoming.getTitle());
            upcoming.setOverview(upcoming.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), DetailUpcoming.class);
            moveWithObjectIntent.putExtra(DetailUpcoming.EXTRA_MOVIE, upcoming);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }


}
