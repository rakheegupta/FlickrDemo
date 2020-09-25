package com.example.flickerdemo.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickerdemo.GlideApp;
import com.example.flickerdemo.MyAppGlideModule;
import com.example.flickerdemo.R;
import com.example.flickerdemo.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecycledMovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    // Store a member variable for the MOvies
    private List<Movie> mMovieList;

    public RecycledMovieAdapter(List<Movie> movies){
        mMovieList=movies;
    }

    @NonNull
    @Override
    // Usually involves inflating a layout from XML and returning the holder
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View movieView = inflater.inflate(R.layout.movie_list_item, parent, false);

        // Return a new holder instance
        MovieViewHolder viewHolder = new MovieViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        //prepare a movie item of the list
        Movie movie = mMovieList.get(position);

        holder.mIvMovie.setImageResource(0);
        String imageUri=movie.getPosterPath();
        int orientation = holder.mIvMovie.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imageUri = movie.getPosterPath();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUri = movie.getBackdropPath();
        }
        
        holder.mTvTitle.setText(movie.getOriginalTitle());
        holder.mTvOverview.setText(movie.getOverview());

        GlideApp.with(holder.mIvMovie.getContext())
                .load(imageUri)
                .override((int)holder.mIvMovie.getResources().getDimension(R.dimen.image_width), (int)holder.mIvMovie.getResources().getDimension(R.dimen.image_height))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.mIvMovie)
        ;
    }

    // Test git
    @Override
    public int getItemCount() {
        return mMovieList.size();
    }


//listview implememntation
    /*
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //prepare a movie item of the list
        Movie movie = getItem(position);

        //inflate if the view is null
        if (convertView==null){
            LayoutInflater inflater =LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.movie_list_item,parent,false);

            // Create a new ViewHolder
            MovieViewHolder movieViewHolder = new MovieViewHolder();
            movieViewHolder.ivMovie=(ImageView) convertView.findViewById(R.id.ivMovie);
            movieViewHolder.tvTitle =(TextView) convertView.findViewById(R.id.tvMovieTitle);
            movieViewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            convertView.setTag(movieViewHolder);
        }

        //retrieve the viewHolder
        MovieViewHolder movieViewHolder= (MovieViewHolder) convertView.getTag();

        //populate the views
        movieViewHolder.ivMovie.setImageResource(0);
        movieViewHolder.tvTitle.setText(movie.getOriginalTitle());
        movieViewHolder.tvOverview.setText(movie.getOverview());

        String imageUri="";
        int orientation =parent.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imageUri = movie.getPosterPath();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUri = movie.getBackdropPath();
        }
        //using picasso
        //Picasso.with(getContext()).load(imageUri).into(movieViewHolder.ivMovie);
        //using glide
        GlideApp.with(getContext())
                .load(imageUri)
                .override((int)parent.getResources().getDimension(R.dimen.image_width), (int)parent.getResources().getDimension(R.dimen.image_height))
                .into(movieViewHolder.ivMovie);
        return convertView;
    }

     */

}
