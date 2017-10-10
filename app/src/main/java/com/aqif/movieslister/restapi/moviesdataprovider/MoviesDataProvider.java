package com.aqif.movieslister.restapi.moviesdataprovider;

import com.aqif.movieslister.restapi.moviesdataprovider.observers.IMoviesDiscoveryObservable;
import com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler.IMoviesDiscoveryCallbackObserver;
import com.aqif.movieslister.restapi.moviesdiscovery.requesthandler.IMoviesDiscoveryRequestHandler;
import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;
import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MoviesDiscoveryDAO;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 10/9/17.
 */

public class MoviesDataProvider implements IMoviesDataProvider
        , IMoviesDiscoveryCallbackObserver {

    private ArrayList<MovieBasic> mMoviesBasic;
    private int pageNumber;

    private IMoviesDiscoveryObservable mMoviesDiscoveryObservable;
    private IMoviesDiscoveryRequestHandler mMoviesDiscoveryRequestHandler;

    @Inject
    public MoviesDataProvider(IMoviesDiscoveryObservable moviesDiscoveryObservable,
                              IMoviesDiscoveryRequestHandler moviesDiscoveryRequestHandler)
    {
        pageNumber = 0;
        mMoviesBasic = new ArrayList<>();
        mMoviesDiscoveryObservable = moviesDiscoveryObservable;
        mMoviesDiscoveryRequestHandler = moviesDiscoveryRequestHandler;
        mMoviesDiscoveryRequestHandler.getMoviesDiscoveryCallbackObservable().registerMoviesDiscoveryCallbackObserver(this);
    }

    @Override
    public IMoviesDiscoveryObservable getMoviesDiscoveryObservable()
    {
        return mMoviesDiscoveryObservable;
    }

    @Override
    public void discoverMovies(String filterStartDate, String filterEndDate)
    {
        pageNumber = 1;
        mMoviesDiscoveryRequestHandler.discoverMovies(pageNumber, filterStartDate, filterEndDate);
    }

    @Override
    public void discoverMoreMovies(String filterStartDate, String filterEndDate)
    {
        pageNumber++;
        mMoviesDiscoveryRequestHandler.discoverMovies(pageNumber, filterStartDate, filterEndDate);
    }

    @Override
    public void onMoviesDiscoverySuccess(MoviesDiscoveryDAO responseData) {
        mMoviesBasic.clear();
        mMoviesBasic.addAll(responseData.getMovieBasics());
        mMoviesDiscoveryObservable.notifyOnMoviesDiscoverySuccess(mMoviesBasic, responseData.getPage(), responseData.getTotalPages());
    }

    @Override
    public void onMoviesDiscoveryFailed(String message) {
        mMoviesDiscoveryObservable.notifyOnMoviesDiscoveryFailure(message);
    }

    @Override
    public void clear()
    {
        mMoviesBasic.clear();
        mMoviesDiscoveryRequestHandler.getMoviesDiscoveryCallbackObservable().unregisterMoviesDiscoveryCallbackObserver(this);
        mMoviesDiscoveryRequestHandler.cancelCalls();
    }
}
