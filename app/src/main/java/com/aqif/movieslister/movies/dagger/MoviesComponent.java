package com.aqif.movieslister.movies.dagger;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.aqif.movieslister.movies.recycler.adapter.IMoviesAdapter;
import com.aqif.movieslister.movies.recycler.observer.RecyclerViewScrollToEndObserver;
import com.aqif.movieslister.movies.viewmodel.IMoviesViewModel;
import com.aqif.movieslister.restapi.moviesdataprovider.dagger.MoviesDataProviderComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by aqifhamid on 10/10/17.
 */


@Singleton
@Component(
        modules = MoviesModule.class,
        dependencies =
                {
                        MoviesDataProviderComponent.class
                })

public interface MoviesComponent {
    IMoviesAdapter provideMoviesAdapter();
    IMoviesViewModel provideMoviesViewModel();
    RecyclerViewScrollToEndObserver provideRecyclerViewScrollToEndObserver();
    GridLayoutManager provideGridLayoutManager();
}
