package com.example.flickerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.flickerdemo.adapters.RecycledMovieAdapter;
import com.example.flickerdemo.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Flicker Async http";
    //https://api.themoviedb.org/3/movie/now_playing?api_key=69694a19e98df7f5c79b13285d536102
    String url="https://api.themoviedb.org/3/movie/now_playing?";
    String key ="69694a19e98df7f5c79b13285d536102";

    ArrayList<Movie> movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies=new ArrayList<>();
        RecyclerView rvItems=(RecyclerView) findViewById(R.id.rvMovies);
        final RecycledMovieAdapter recycledMovieListAdapter = new RecycledMovieAdapter(this,movies);
        rvItems.setAdapter(recycledMovieListAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("api_key",key);

        JsonHttpResponseHandler movieResponse =new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString());
                JSONArray movieJsonResults=null;
                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    recycledMovieListAdapter.notifyDataSetChanged();
                    System.out.println(movies.toString());
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Toast.makeText(getBaseContext(), responseString, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                System.out.println(errorResponse.toString());
            }
        };
        client.get(url,params, movieResponse);
    }
}