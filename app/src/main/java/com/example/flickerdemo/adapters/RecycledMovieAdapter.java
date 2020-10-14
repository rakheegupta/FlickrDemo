package com.example.flickerdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickerdemo.GlideApp;
import com.example.flickerdemo.R;
import com.example.flickerdemo.SummaryActivity;
import com.example.flickerdemo.models.Movie;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class RecycledMovieAdapter extends RecyclerView.Adapter
        <RecycledMovieAdapter.MovieViewHolder> {

    // Store a member variable for the MOvies
    private List<Movie> mMovieList;
    Context mContext;
    private final int  LESS_POPULAR = 0,POPULAR = 1;

    public RecycledMovieAdapter(Context context,List<Movie> movies){
        mMovieList=movies;
        mContext=context;
    }

    @NonNull
    @Override
    // Usually involves inflating a layout from XML and returning the holder
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.movie_list_item, parent, false);
        // Return a new holder instance
        MovieViewHolder viewHolder = new MovieViewHolder(movieView);
        return viewHolder;

        /*
        switch (viewType) {
            case LESS_POPULAR:
                View movieView = inflater.inflate(R.layout.movie_list_item, parent, false);
                // Return a new holder instance
                viewHolder = new MovieViewHolder(movieView);

            default:
                View popularMovieView = inflater.inflate(R.layout.movie_list_item, parent, false);
                viewHolder = new FiveStarMovieViewHolder(popularMovieView);
        }
        return viewHolder;

         */
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        //prepare a movie item of the list
        Movie movie = mMovieList.get(position);

        holder.mIvMovie.setImageResource(0);
        String imageUri=movie.getPosterPath();

        int orientation = mContext.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imageUri = movie.getPosterPath();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUri = movie.getBackdropPath();
        }
        double stars = movie.getRating();
        holder.mRbStars.setNumStars((int)stars/2);

         switch (holder.getItemViewType()) {
             case LESS_POPULAR:
                 holder.mTvTitle.setText(movie.getOriginalTitle());
                 holder.mTvOverview.setText(movie.getOverview());
                 GlideApp.with(holder.mIvMovie.getContext())
                         .load(imageUri)
                         .override((int) mContext.getResources().getDimension(R.dimen.image_width), (int) mContext.getResources().getDimension(R.dimen.image_height))
                         .placeholder(R.drawable.ic_launcher_foreground)
                         .transform(new RoundedCornersTransformation(30, 10))
                         .into(holder.mIvMovie);
                 break;
             default:
                 imageUri = movie.getBackdropPath();
                 holder.mTvTitle.setText("");
                 holder.mTvOverview.setText("");
                 GlideApp.with(mContext)
                         .load(imageUri)
                         .override((int) mContext.getResources().getDimension(R.dimen.big_image_width), (int) mContext.getResources().getDimension(R.dimen.big_image_height))
                         .transform(new RoundedCornersTransformation(30, 10))
                         .placeholder(R.drawable.ic_launcher_foreground)
                         .into(holder.mIvMovie);
         }
    }

    // Test git
    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    @Override
    public int getItemViewType(int position) {
        double stars = mMovieList.get(position).getRating();
        if (stars<7)
            return LESS_POPULAR;
        else
            return POPULAR;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Your holder should contain a member variable
        // for any view that will be set as you render a row

        public ImageView mIvMovie;
        public TextView mTvTitle;
        public TextView mTvOverview;
        public RatingBar mRbStars;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public MovieViewHolder(View itemView){
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            mIvMovie = (ImageView) itemView.findViewById(R.id.ivMovie);
            mTvTitle =(TextView)itemView.findViewById(R.id.tvMovieTitle);
            mTvOverview=(TextView)itemView.findViewById(R.id.tvOverview);
            mRbStars=(RatingBar)itemView.findViewById(R.id.rbStars);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view){
            int position =  getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Movie movie=mMovieList.get(position);
                //startActivitySummary
                Intent intent  =new Intent(mContext, SummaryActivity.class);
                intent.putExtra("selected_movie",movie);
                mContext.startActivity(intent);
            }
        }
    }
}
