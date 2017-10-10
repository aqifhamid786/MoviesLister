package com.aqif.movieslister.movies.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;

import com.aqif.movieslister.movies.recycler.observer.RecyclerViewScrollToEndObserver;
import com.aqif.movieslister.restapi.moviesdataprovider.IMoviesDataProvider;
import com.aqif.movieslister.restapi.moviesdataprovider.observers.IMoviesDiscoveryObserver;
import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 10/9/17.
 */

public class MoviesViewModel implements IMoviesViewModel,
        IMoviesDiscoveryObserver,
        RecyclerViewScrollToEndObserver.IOnLoadMoreRecycleViewDataListner
{
    private IMoviesDataProvider mMoviesDataProvider;
    private RecyclerViewScrollToEndObserver mRecyclerViewScrollToEndObserver;
    private String mFilterStartDate = "";
    private String mFilterEndDate = "";

    public final ObservableList<MovieBasic> movies = new ObservableArrayList<>();

    public final ObservableBoolean isDataLoading = new ObservableBoolean(true);
    public final ObservableBoolean noDataToShow = new ObservableBoolean(false);
    public final ObservableBoolean isLoadingMoreData = new ObservableBoolean(false);

    @Inject
    public MoviesViewModel(IMoviesDataProvider moviesDataProvider, RecyclerViewScrollToEndObserver recyclerViewScrollToEndObserver)
    {
        mRecyclerViewScrollToEndObserver = recyclerViewScrollToEndObserver;
        mMoviesDataProvider = moviesDataProvider;
        noDataToShow.set(false);
    }

    @Override
    public void onActivityCreate()
    {
        mRecyclerViewScrollToEndObserver.setOnLoadMoreRecycleViewDataListner(this);
        mMoviesDataProvider.getMoviesDiscoveryObservable().registerMoviesDiscoveryObserver(this);
        mRecyclerViewScrollToEndObserver.setLoadingData(true);
        isDataLoading.set(true);
        mMoviesDataProvider.discoverMovies(mFilterStartDate, mFilterEndDate);
    }

    @Override
    public void onAcitivityStart() {}

    @Override
    public void onActivityStop() {}

    @Override
    public void onActivityDestroy() {

        mRecyclerViewScrollToEndObserver.setOnLoadMoreRecycleViewDataListner(null);
        mMoviesDataProvider.getMoviesDiscoveryObservable().unregisterMoviesDiscoveryObserver(this);
        mMoviesDataProvider.clear();
    }

    @Override
    public void onMoviesDiscoverySuccess(ArrayList<MovieBasic> moviesBasic, int page, int totalPage)
    {
        movies.addAll(moviesBasic);
        isDataLoading.set(false);
        isLoadingMoreData.set(false);
        noDataToShow.set(movies.size()==0);
        mRecyclerViewScrollToEndObserver.setLoadingData(page>=totalPage);
    }

    @Override
    public void onMoviesDiscoveryFailure(String message)
    {
        isDataLoading.set(false);
        isLoadingMoreData.set(false);
        noDataToShow.set(true);
        mRecyclerViewScrollToEndObserver.setLoadingData(true);
    }

    @Override
    public void onLoadMoreRecycleViewDataListner(int page, int totalItemsCount, RecyclerView view)
    {
        mRecyclerViewScrollToEndObserver.setLoadingData(true);
        isLoadingMoreData.set(true);
        mMoviesDataProvider.discoverMoreMovies(mFilterStartDate, mFilterEndDate);
    }

    // callback for date range picker dialog.

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd)
    {

        Calendar calendarStart = Calendar.getInstance();
        calendarStart.set(year, monthOfYear, dayOfMonth);

        Calendar calendarEnd = Calendar.getInstance();
        calendarStart.set(yearEnd, monthOfYearEnd, dayOfMonthEnd);

        if(calendarStart.compareTo(calendarEnd)<=0)
        {
            mFilterStartDate = DateFormat.format("yyyy-MM-dd", calendarStart).toString();
            mFilterEndDate = DateFormat.format("yyyy-MM-dd", calendarEnd).toString();

            movies.clear();
            mRecyclerViewScrollToEndObserver.setLoadingData(true);
            isDataLoading.set(true);

            mMoviesDataProvider.discoverMovies(mFilterStartDate, mFilterEndDate);
        }
    }
}
