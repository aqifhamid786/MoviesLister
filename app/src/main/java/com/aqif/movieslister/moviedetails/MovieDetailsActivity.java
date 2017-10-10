package com.aqif.movieslister.moviedetails;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aqif.movieslister.R;
import com.aqif.movieslister.databinding.MovieDetailActivityBinding;
import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;

/**
 * Created by aqifhamid on 10/10/17.
 */

public class MovieDetailsActivity extends AppCompatActivity
{

    public static String MOVIE_TAG = "movie_tag";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        MovieBasic movieBasic = getIntent().getExtras().getParcelable(MOVIE_TAG);
        MovieDetailActivityBinding mBinding = DataBindingUtil.setContentView(this, R.layout.movie_detail_activity);
        mBinding.setMovie(movieBasic);
    }

}
