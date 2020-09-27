package com.example.flickerdemo.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickerdemo.GlideApp;
import com.example.flickerdemo.R;
import com.example.flickerdemo.models.Movie;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class RecycledMovieAdapter extends RecyclerView.Adapter
        <MovieViewHolder> {

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
        MovieViewHolder viewHolder;

        View movieView = inflater.inflate(R.layout.movie_list_item, parent, false);
        // Return a new holder instance
        viewHolder = new MovieViewHolder(movieView);
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
                 //holder.mIvMovie.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                 //holder.mIvMovie.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                 //holder.mIvMovie.requestLayout();
                 GlideApp.with(holder.mIvMovie.getContext())
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
