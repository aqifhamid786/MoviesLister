package com.aqif.movieslister.restapi.moviesdataprovider;

import com.aqif.movieslister.restapi.moviesdataprovider.observers.IMoviesDiscoveryObservable;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;

/**
 * Created by aqifhamid on 10/9/17.
 */

public interface IMoviesDataProvider{

    void discoverMovies(String filterStartDate, String filterEndDate);
    void discoverMoreMovies(String filterStartDate, String filterEndDate);
    void clear();

    IMoviesDiscoveryObservable getMoviesDiscoveryObservable();

}
