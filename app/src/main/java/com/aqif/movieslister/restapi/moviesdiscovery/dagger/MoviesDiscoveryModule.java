package com.aqif.movieslister.restapi.moviesdiscovery.dagger;

import com.aqif.movieslister.restapi.moviesdiscovery.MoviesDiscoveryConstants;
import com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler.IMoviesDiscoveryCallbackHandler;
import com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler.IMoviesDiscoveryCallbackObserver;
import com.aqif.movieslister.restapi.moviesdiscovery.callbackhandler.MoviesDiscoveryCallbackHandler;
import com.aqif.movieslister.restapi.moviesdiscovery.requesthandler.IMoviesDiscoveryRequestHandler;
import com.aqif.movieslister.restapi.moviesdiscovery.requesthandler.IMoviesDiscoveryRetrofitService;
import com.aqif.movieslister.restapi.moviesdiscovery.requesthandler.MoviesDiscoveryRequestHandler;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Module
public class MoviesDiscoveryModule
{

    @Provides
    public IMoviesDiscoveryCallbackHandler provideMoviesDiscoveryCallbackHandler()
    {
        return new MoviesDiscoveryCallbackHandler(new ArrayList<IMoviesDiscoveryCallbackObserver>());
    }

    @Provides
    IMoviesDiscoveryRetrofitService provideMoviesDiscoveryRetrofitService()
    {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MoviesDiscoveryConstants.MoviesDiscoveryRequestURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        return retrofit.create(IMoviesDiscoveryRetrofitService.class);
    }

    @Provides
    IMoviesDiscoveryRequestHandler provideMoviesDiscoveryRequestHandler(IMoviesDiscoveryRetrofitService moviesDiscoveryRetrofitService
            , IMoviesDiscoveryCallbackHandler moviesDiscoveryCallbackHandler)
    {
        return new MoviesDiscoveryRequestHandler(moviesDiscoveryRetrofitService, moviesDiscoveryCallbackHandler);
    }
}
