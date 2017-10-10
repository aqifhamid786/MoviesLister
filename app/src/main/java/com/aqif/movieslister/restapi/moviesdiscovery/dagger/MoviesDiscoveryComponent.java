package com.aqif.movieslister.restapi.moviesdiscovery.dagger;

import com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler.IMoviesDiscoveryCallbackHandler;
import com.aqif.movieslister.restapi.moviesdiscovery.requesthandler.IMoviesDiscoveryRequestHandler;
import com.aqif.movieslister.restapi.moviesdiscovery.requesthandler.IMoviesDiscoveryRetrofitService;

import dagger.Component;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Component(modules={MoviesDiscoveryModule.class})
public interface MoviesDiscoveryComponent
{
    IMoviesDiscoveryCallbackHandler getMoviesDiscoveryCallbackHandler();
    IMoviesDiscoveryRequestHandler getMoviesDiscoveryRequestHandler();
    IMoviesDiscoveryRetrofitService getMoviesDiscoveryRetrofitService();
}
