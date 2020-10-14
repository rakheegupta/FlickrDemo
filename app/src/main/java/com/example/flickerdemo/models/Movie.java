package com.example.flickerdemo.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {
    String posterPath;
    String originalTitle;
    String overview;
    String backdropPath;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    double rating;

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w500%s",posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w500%s",backdropPath);
    }
    public Movie(JSONObject movieobject) throws JSONException {
        posterPath=movieobject.getString("poster_path");
        originalTitle=movieobject.getString("original_title");
        overview=movieobject.getString("overview");
        backdropPath=movieobject.getString("backdrop_path");
        rating=movieobject.getDouble("vote_average");
    }
    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> movieList = new ArrayList<Movie>(array.length());
        try {
            for (int i = 0; i < array.length(); i++) {
                Movie result = new Movie(array.getJSONObject(i));
                movieList.add(result);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }
}
