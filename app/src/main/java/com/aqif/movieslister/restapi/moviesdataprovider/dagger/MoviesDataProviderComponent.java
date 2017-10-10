package com.aqif.movieslister.restapi.moviesdataprovider.dagger;

import com.aqif.movieslister.restapi.moviesdataprovider.IMoviesDataProvider;
import com.aqif.movieslister.restapi.moviesdataprovider.observers.IMoviesDiscoveryObservable;
import com.aqif.movieslister.restapi.moviesdiscovery.dagger.MoviesDiscoveryComponent;

import dagger.Component;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Component(
        modules = MoviesDataProviderModule.class,
        dependencies =
                {
                        MoviesDiscoveryComponent.class
                })

public interface MoviesDataProviderComponent
{
    IMoviesDiscoveryObservable provideMoviesDiscoveryObservable();
    IMoviesDataProvider provideMoviesDataProvider();
}
