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
import com.appsnipp.milo.detail.DetailPopular;
import com.appsnipp.milo.model.Popular;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MovieViewHolder> {

    private ArrayList<Popular> mData = new ArrayList<>();
    public void setData(ArrayList<Popular> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_popular, viewGroup, false);
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
        void bind(Popular popular) {
            String vote_average = Double.toString(popular.getRate());
            String url_image = "https://image.tmdb.org/t/p/w185" + popular.getPhoto();

            tvTitle.setText(popular.getTitle());
            tvDate.setText(popular.getRelease_date());
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
            Popular popular = mData.get(position);
//
            popular.setTitle(popular.getTitle());
            popular.setOverview(popular.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), DetailPopular.class);
            moveWithObjectIntent.putExtra(DetailPopular.EXTRA_MOVIE, popular);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }


}
