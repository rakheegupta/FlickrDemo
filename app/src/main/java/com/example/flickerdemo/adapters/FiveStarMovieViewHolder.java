package com.example.flickerdemo.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flickerdemo.R;

public class FiveStarMovieViewHolder extends RecyclerView.ViewHolder {
    public ImageView mIvMovie;
    FiveStarMovieViewHolder(View view){
        super(view);
        mIvMovie = (ImageView) itemView.findViewById(R.id.ivMovie);

    }
}
