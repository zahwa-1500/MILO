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
import com.appsnipp.milo.detail.DetailNowPlaying;
import com.appsnipp.milo.model.NowPlaying;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.MovieViewHolder> {

    private ArrayList<NowPlaying> mData = new ArrayList<>();
    public void setData(ArrayList<NowPlaying> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_now_playing, viewGroup, false);
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
        void bind(NowPlaying nowPlaying) {
            String vote_average = Double.toString(nowPlaying.getRate());
            String url_image = "https://image.tmdb.org/t/p/w185" + nowPlaying.getPhoto();

            tvTitle.setText(nowPlaying.getTitle());
            tvDate.setText(nowPlaying.getRelease_date());
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
            NowPlaying nowPlaying = mData.get(position);
//
            nowPlaying.setTitle(nowPlaying.getTitle());
            nowPlaying.setOverview(nowPlaying.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), DetailNowPlaying.class);
            moveWithObjectIntent.putExtra(DetailNowPlaying.EXTRA_MOVIE, nowPlaying);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }


}
