package com.aqif.movieslister.restapi.moviesdiscovery.requesthandler;


import com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler.IMoviesDiscoveryCallbackHandler;
import com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler.IMoviesDiscoveryCallbackObservable;
import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MoviesDiscoveryDAO;

import javax.inject.Inject;

import retrofit2.Call;

public class MoviesDiscoveryRequestHandler implements IMoviesDiscoveryRequestHandler
{

    private IMoviesDiscoveryRetrofitService mMoviesDiscoveryRetrofitService;
    private IMoviesDiscoveryCallbackHandler mMoviesDiscoveryCallbackHandler;


    @Inject
    public MoviesDiscoveryRequestHandler( IMoviesDiscoveryRetrofitService MoviesDiscoveryRetrofitService
            , IMoviesDiscoveryCallbackHandler MoviesDiscoveryCallbackHandler)
    {
        mMoviesDiscoveryRetrofitService = MoviesDiscoveryRetrofitService;
        mMoviesDiscoveryCallbackHandler = MoviesDiscoveryCallbackHandler;
    }

    @Override
    public IMoviesDiscoveryCallbackObservable getMoviesDiscoveryCallbackObservable()
    {
        return mMoviesDiscoveryCallbackHandler;
    }

    @Override
    public void discoverMovies(int page, String filterStartDate, String filterEndDate)
    {
        if(mMoviesDiscoveryCallbackHandler.getDiscoveryCall()!=null)
        {
            mMoviesDiscoveryCallbackHandler.getDiscoveryCall().cancel();
        }

        Call<MoviesDiscoveryDAO> call = null;
        if(filterStartDate!=null && !filterStartDate.isEmpty()
                && filterEndDate!=null && !filterEndDate.isEmpty()) {
            call = mMoviesDiscoveryRetrofitService.discoverMoviesWithRange("1d32f237e2852d2ad455fbca87f282bf", "en-US", false, false, page, filterStartDate, filterEndDate);
        }else{
            call = mMoviesDiscoveryRetrofitService.discoverMovies("1d32f237e2852d2ad455fbca87f282bf", "en-US", false, false, page);
        }

        mMoviesDiscoveryCallbackHandler.setDiscoveryCall(call);
        call.enqueue(mMoviesDiscoveryCallbackHandler);
    }

    @Override
    public void cancelCalls()
    {
        if(mMoviesDiscoveryCallbackHandler.getDiscoveryCall()!=null)
        {
            mMoviesDiscoveryCallbackHandler.getDiscoveryCall().cancel();
        }
    }

}
