package com.aqif.movieslister.restapi.moviesdataprovider.dagger;

import com.aqif.movieslister.restapi.moviesdataprovider.IMoviesDataProvider;
import com.aqif.movieslister.restapi.moviesdataprovider.MoviesDataProvider;
import com.aqif.movieslister.restapi.moviesdataprovider.observers.IMoviesDiscoveryObservable;
import com.aqif.movieslister.restapi.moviesdataprovider.observers.IMoviesDiscoveryObserver;
import com.aqif.movieslister.restapi.moviesdataprovider.observers.MoviesDiscoveryObservable;
import com.aqif.movieslister.restapi.moviesdiscovery.requesthandler.IMoviesDiscoveryRequestHandler;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Module
public class MoviesDataProviderModule
{

    @Provides
    public IMoviesDiscoveryObservable provideMoviesDiscoveryObservable()
    {
        return new MoviesDiscoveryObservable(new ArrayList<IMoviesDiscoveryObserver>());
    }

    @Provides
    public IMoviesDataProvider provideMoviesDataProvider(IMoviesDiscoveryObservable moviesDiscoveryObservable,
                                                         IMoviesDiscoveryRequestHandler moviesDiscoveryRequestHandler)
    {
        return new MoviesDataProvider(moviesDiscoveryObservable, moviesDiscoveryRequestHandler);
    }


}
