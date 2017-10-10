package com.aqif.movieslister.movies;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;

import com.aqif.movieslister.BR;
import com.aqif.movieslister.R;
import com.aqif.movieslister.databinding.MoviesActivityBinding;
import com.aqif.movieslister.movies.dagger.DaggerMoviesComponent;
import com.aqif.movieslister.movies.dagger.MoviesComponent;
import com.aqif.movieslister.movies.dagger.MoviesModule;
import com.aqif.movieslister.movies.recycler.adapter.MoviesAdapter;
import com.aqif.movieslister.movies.recycler.observer.RecyclerViewScrollToEndObserver;
import com.aqif.movieslister.movies.viewmodel.IMoviesViewModel;
import com.aqif.movieslister.restapi.moviesdataprovider.dagger.DaggerMoviesDataProviderComponent;
import com.aqif.movieslister.restapi.moviesdataprovider.observers.IMoviesDiscoveryObserver;
import com.aqif.movieslister.restapi.moviesdiscovery.dagger.DaggerMoviesDiscoveryComponent;
import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by aqifhamid on 10/9/17.
 */

public class MoviesActivity extends AppCompatActivity{

    public static final String DATEPICKER_TAG = "datepicker";

    private MoviesActivityBinding mBinding;
    private MoviesComponent mMoviesComponent;
    private IMoviesViewModel moviesViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.movies_activity);
        setupDagger();
        setupViewsAndVM();
        setupImageLoader();
        moviesViewModel.onActivityCreate();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        moviesViewModel.onAcitivityStart();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        moviesViewModel.onActivityStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        moviesViewModel.onActivityDestroy();
    }

    private void setupDagger()
    {
        mMoviesComponent = DaggerMoviesComponent.builder()
                .moviesModule(new MoviesModule(this))
                .moviesDataProviderComponent(DaggerMoviesDataProviderComponent.builder()
                        .moviesDiscoveryComponent(DaggerMoviesDiscoveryComponent.builder().build())
                        .build())
                        .build();
    }

    private void setupViewsAndVM()
    {
        // setup recyclerview
        ((SimpleItemAnimator) mBinding.recyclerViewMovies.getItemAnimator()).setSupportsChangeAnimations(false);
        mBinding.recyclerViewMovies.setLayoutManager(mMoviesComponent.provideGridLayoutManager());
        mBinding.recyclerViewMovies.addOnScrollListener(mMoviesComponent.provideRecyclerViewScrollToEndObserver());
        mBinding.recyclerViewMovies.setAdapter((MoviesAdapter) mMoviesComponent.provideMoviesAdapter());
        mBinding.toolbar.setTitle(R.string.app_name);

        // setup viewmodel
        moviesViewModel = mMoviesComponent.provideMoviesViewModel();
        mBinding.setVariable(BR.viewmodel, moviesViewModel);

        // setup Fab Button
        FloatingActionButton fab = findViewById(R.id.fab_date_filter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        moviesViewModel,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.setYearRange(1903, 2017);
                datePickerDialog.show(getFragmentManager(), DATEPICKER_TAG);
            }
        });
    }

    private void setupImageLoader()
    {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();

        ImageLoader.getInstance().init(config);
    }

}
