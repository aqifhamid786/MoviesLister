package com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler;


import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MoviesDiscoveryDAO;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface IMoviesDiscoveryCallbackObserver
{
    void onMoviesDiscoverySuccess(MoviesDiscoveryDAO responseData);
    void onMoviesDiscoveryFailed(String message);
}
