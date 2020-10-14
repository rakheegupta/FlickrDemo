package com.example.flickerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flickerdemo.models.Movie;

import org.w3c.dom.Text;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Movie selectedMovie = (Movie)getIntent().getSerializableExtra("selected_movie");
        ImageView ivMovie = (ImageView) findViewById(R.id.ivMovieSummary);
        TextView tvTitle = (TextView) findViewById(R.id.tvMovieName);
        TextView tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        TextView tvDescription = (TextView) findViewById(R.id.tvMovieSummaryDescription);
        RatingBar rbStars =(RatingBar) findViewById(R.id.rbStars);

        GlideApp.with(this)
                .load(selectedMovie.getBackdropPath())
                //.override(R.dimen.big_image_width,R.dimen.big_image_height)
                .transform(new RoundedCornersTransformation(30,10))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivMovie);
        tvTitle.setText(selectedMovie.getOriginalTitle());
        tvDescription.setText(selectedMovie.getOverview());
        rbStars.setNumStars((int)selectedMovie.getRating());
    }
}