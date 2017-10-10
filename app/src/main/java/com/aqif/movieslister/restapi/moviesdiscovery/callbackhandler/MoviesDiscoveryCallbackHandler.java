package com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler;

import com.aqif.movieslister.restapi.moviesdiscovery.MoviesDiscoveryConstants;
import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MoviesDiscoveryDAO;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class MoviesDiscoveryCallbackHandler implements IMoviesDiscoveryCallbackHandler
{

    private Call<MoviesDiscoveryDAO> mDiscoveryCall;
    private List<IMoviesDiscoveryCallbackObserver> mMoviesDiscoveryCallbackObservers;

    @Inject
    public MoviesDiscoveryCallbackHandler(List<IMoviesDiscoveryCallbackObserver> moviesDiscoveryerCallbackObservers)
    {
        mMoviesDiscoveryCallbackObservers = moviesDiscoveryerCallbackObservers;
    }

    @Override
    public void registerMoviesDiscoveryCallbackObserver(IMoviesDiscoveryCallbackObserver moviesDiscoveryerCallbackObserver)
    {
        if(!mMoviesDiscoveryCallbackObservers.contains(moviesDiscoveryerCallbackObserver))
        {
            mMoviesDiscoveryCallbackObservers.add(moviesDiscoveryerCallbackObserver);
        }
    }

    @Override
    public void unregisterMoviesDiscoveryCallbackObserver(IMoviesDiscoveryCallbackObserver moviesDiscoveryerCallbackObserver)
    {
        if(mMoviesDiscoveryCallbackObservers.contains(moviesDiscoveryerCallbackObserver))
        {
            mMoviesDiscoveryCallbackObservers.remove(moviesDiscoveryerCallbackObserver);
        }
    }

    @Override
    public void onResponse(Call<MoviesDiscoveryDAO> call, Response<MoviesDiscoveryDAO> response)
    {
        mDiscoveryCall = null;

        if(response.body()!=null)
        {
            for(int lop = 0; lop< mMoviesDiscoveryCallbackObservers.size(); lop++)
            {
                mMoviesDiscoveryCallbackObservers.get(lop).onMoviesDiscoverySuccess(response.body());
            }
        }
        else
        {
            for(int lop = 0; lop< mMoviesDiscoveryCallbackObservers.size(); lop++)
            {
                mMoviesDiscoveryCallbackObservers.get(lop).onMoviesDiscoveryFailed(MoviesDiscoveryConstants.MoviesDiscoveryRequestFailMessage);
            }
        }
    }

    @Override
    public void onFailure(Call<MoviesDiscoveryDAO> call, Throwable t)
    {
        mDiscoveryCall = null;
        for(int lop = 0; lop< mMoviesDiscoveryCallbackObservers.size(); lop++)
        {
            mMoviesDiscoveryCallbackObservers.get(lop).onMoviesDiscoveryFailed(MoviesDiscoveryConstants.MoviesDiscoveryRequestFailMessage);
        }
    }

    @Override
    public void setDiscoveryCall(Call<MoviesDiscoveryDAO> call)
    {
        mDiscoveryCall = call;
    }

    @Override
    public Call<MoviesDiscoveryDAO> getDiscoveryCall()
    {
        return mDiscoveryCall;
    }
}
