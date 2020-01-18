package com.appsnipp.milo.detail;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.milo.R;
import com.appsnipp.milo.model.Popular;
import com.bumptech.glide.Glide;

public class DetailPopular extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    TextView tv_judul, tv_rating, tv_sinopsis;

    private ProgressBar progressBar;
    ImageView imagePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_popular);
        tv_judul = findViewById(R.id.tv_title);
        tv_rating = findViewById(R.id.tv_rate);
        tv_sinopsis = findViewById(R.id.detail_sinopsis);
        imagePhoto = findViewById(R.id.photo);

        progressBar = findViewById(R.id.progressDetailM);
        progressBar.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(2000);
                }
                catch (Exception e) { }
                handler.post(new Runnable() {
                    public void run() {
                        Popular popular = getIntent().getParcelableExtra(EXTRA_MOVIE);

                        String vote_average = Double.toString(popular.getRate());
                        String url_image = "https://image.tmdb.org/t/p/w185" + popular.getPhoto();

                        tv_judul.setText(popular.getTitle());
                        tv_rating.setText(vote_average);
                        tv_sinopsis.setText(popular.getOverview());
                        Glide.with(getApplicationContext())
                                .load(url_image)
                                .placeholder(R.color.colorAccent)
                                .dontAnimate()
                                .into(imagePhoto);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }

}