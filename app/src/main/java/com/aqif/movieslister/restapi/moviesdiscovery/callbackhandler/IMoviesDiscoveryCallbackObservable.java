package com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface IMoviesDiscoveryCallbackObservable {
    void registerMoviesDiscoveryCallbackObserver(IMoviesDiscoveryCallbackObserver moviesDiscoveryCallbackObserver);
    void unregisterMoviesDiscoveryCallbackObserver(IMoviesDiscoveryCallbackObserver moviesDiscoveryCallbackObserver);
}
