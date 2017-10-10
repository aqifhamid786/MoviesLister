package com.aqif.movieslister.restapi.moviesdiscovery.requesthandler;


import com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler.IMoviesDiscoveryCallbackObservable;

public interface IMoviesDiscoveryRequestHandler
{
    IMoviesDiscoveryCallbackObservable getMoviesDiscoveryCallbackObservable();

    void discoverMovies(int page, String filterStartDate, String filterEndDate);
    void cancelCalls();
}
