package com.aqif.movieslister.restapi.moviesdataprovider.observers;


import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;

import java.util.ArrayList;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface IMoviesDiscoveryObserver
{
    void onMoviesDiscoverySuccess(ArrayList<MovieBasic> moviesBasic, int page, int totalPages);
    void onMoviesDiscoveryFailure(String message);
}
