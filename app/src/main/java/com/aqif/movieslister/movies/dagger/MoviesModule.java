package com.aqif.movieslister.movies.dagger;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.aqif.movieslister.movies.recycler.adapter.IMoviesAdapter;
import com.aqif.movieslister.movies.recycler.adapter.MoviesAdapter;
import com.aqif.movieslister.movies.recycler.observer.RecyclerViewScrollToEndObserver;
import com.aqif.movieslister.movies.viewmodel.IMoviesViewModel;
import com.aqif.movieslister.movies.viewmodel.MoviesViewModel;
import com.aqif.movieslister.restapi.moviesdataprovider.IMoviesDataProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 10/10/17.
 */

@Singleton
@Module
public class MoviesModule
{

    private Context mContext;

    public MoviesModule(Context context)
    {
        mContext = context;
    }

    @Singleton
    @Provides
    public GridLayoutManager provideGridLayoutManager()
    {
        return new GridLayoutManager(mContext, 2);
    }

    @Singleton
    @Provides
    public RecyclerViewScrollToEndObserver provideRecyclerViewScrollToEndObserver(GridLayoutManager gridLayoutManager)
    {
        return new RecyclerViewScrollToEndObserver(gridLayoutManager);
    }

    @Provides
    public IMoviesAdapter provideMoviesAdapter()
    {
        return new MoviesAdapter();
    }

    @Singleton
    @Provides
    public IMoviesViewModel provideMoviesViewModel(IMoviesDataProvider moviesDataProvider, RecyclerViewScrollToEndObserver recyclerViewScrollToEndObserver)
    {
        return new MoviesViewModel(moviesDataProvider, recyclerViewScrollToEndObserver);
    }

}
