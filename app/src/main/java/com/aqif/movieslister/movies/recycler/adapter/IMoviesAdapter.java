package com.aqif.movieslister.movies.recycler.adapter;

import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;

import java.util.List;

/**
 * Created by aqifhamid on 10/10/17.
 */

public interface IMoviesAdapter {
    void setLoadingMoreItem(boolean loadingMoreItem);
    void updateData(List<MovieBasic> movies);
}
