package com.aqif.movieslister.movies.handlers;

import android.content.Intent;
import android.view.View;

import com.aqif.movieslister.moviedetails.MovieDetailsActivity;
import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;

/**
 * Created by aqifhamid on 10/10/17.
 */

public class MovieInputHandler {

    public void onMovieClicked(View view, MovieBasic movie)
    {
        Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.MOVIE_TAG, movie);
        view.getContext().startActivity(intent);
    }
}
