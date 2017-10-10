package com.aqif.movieslister.movies.binder;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.aqif.movieslister.movies.recycler.adapter.IMoviesAdapter;
import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;

import java.util.List;

public class MoviesRecyclerBinder
{
    @BindingAdapter("app:movies")
    public static void setMovies(RecyclerView recyclerView, List<MovieBasic> movies)
    {
        IMoviesAdapter adapter = (IMoviesAdapter) recyclerView.getAdapter();
        if (adapter != null)
        {
            adapter.updateData(movies);
        }
    }

    @BindingAdapter("app:isLoadingMoreData")
    public static void setIsLoadingMoreData(RecyclerView recyclerView, boolean isLoadingMoreData)
    {
        IMoviesAdapter adapter = (IMoviesAdapter) recyclerView.getAdapter();
        if (adapter != null)
        {
            adapter.setLoadingMoreItem(isLoadingMoreData);
        }
    }
}
