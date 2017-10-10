package com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler;


import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MoviesDiscoveryDAO;

import retrofit2.Call;
import retrofit2.Callback;

public interface IMoviesDiscoveryCallbackHandler extends IMoviesDiscoveryCallbackObservable, Callback<MoviesDiscoveryDAO>
{
    void setDiscoveryCall(Call<MoviesDiscoveryDAO> call);
    Call<MoviesDiscoveryDAO> getDiscoveryCall();
}
