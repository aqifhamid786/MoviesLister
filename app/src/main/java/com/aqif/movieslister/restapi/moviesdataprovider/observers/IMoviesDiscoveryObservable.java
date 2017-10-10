package com.aqif.movieslister.restapi.moviesdataprovider.observers;


import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;

import java.util.ArrayList;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface IMoviesDiscoveryObservable
{
    void registerMoviesDiscoveryObserver(IMoviesDiscoveryObserver moviesDiscoveryObserver);
    void unregisterMoviesDiscoveryObserver(IMoviesDiscoveryObserver moviesDiscoveryObserver);

    void notifyOnMoviesDiscoverySuccess(ArrayList<MovieBasic> mMoviesBasic, int page, int totalPages);
    void notifyOnMoviesDiscoveryFailure(String message);
}
