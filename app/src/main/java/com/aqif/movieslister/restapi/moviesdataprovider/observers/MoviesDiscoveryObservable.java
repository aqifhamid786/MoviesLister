package com.aqif.movieslister.restapi.moviesdataprovider.observers;


import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class MoviesDiscoveryObservable implements IMoviesDiscoveryObservable {

    protected List<IMoviesDiscoveryObserver> mMoviesDiscoveryObservers;

    @Inject
    public MoviesDiscoveryObservable(List<IMoviesDiscoveryObserver> moviesDiscoveryObservers)
    {
        mMoviesDiscoveryObservers = moviesDiscoveryObservers;
    }


    @Override
    public void registerMoviesDiscoveryObserver(IMoviesDiscoveryObserver moviesDiscoveryObserver)
    {
        if(!mMoviesDiscoveryObservers.contains(moviesDiscoveryObserver))
        {
            mMoviesDiscoveryObservers.add(moviesDiscoveryObserver);
        }
    }

    @Override
    public void unregisterMoviesDiscoveryObserver(IMoviesDiscoveryObserver moviesDiscoveryObserver)
    {
        if(mMoviesDiscoveryObservers.contains(moviesDiscoveryObserver))
        {
            mMoviesDiscoveryObservers.remove(moviesDiscoveryObserver);
        }
    }

    @Override
    public void notifyOnMoviesDiscoverySuccess(ArrayList<MovieBasic> moviesBasic, int page, int totalPages)
    {
        for(int lop = 0; lop< mMoviesDiscoveryObservers.size(); lop++)
        {
            mMoviesDiscoveryObservers.get(lop).onMoviesDiscoverySuccess(moviesBasic, page, totalPages);
        }
    }

    @Override
    public void notifyOnMoviesDiscoveryFailure(String message)
    {
        for(int lop = 0; lop< mMoviesDiscoveryObservers.size(); lop++)
        {
            mMoviesDiscoveryObservers.get(lop).onMoviesDiscoveryFailure(message);
        }
    }

}
