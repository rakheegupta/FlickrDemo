package com.example.flickerdemo.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flickerdemo.R;

// Provide a direct reference to each of the views within a data item
// Used to cache the views within the item layout for fast access
public class MovieViewHolder extends RecyclerView.ViewHolder {

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
    }
}
