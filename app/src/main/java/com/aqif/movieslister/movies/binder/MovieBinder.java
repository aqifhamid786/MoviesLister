package com.aqif.movieslister.movies.binder;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.aqif.movieslister.R;
import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by aqifhamid on 10/10/17.
 */

public class MovieBinder {


    private final static String baseUrl = "https://image.tmdb.org/t/p/w342";

    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView imageView, MovieBasic movieBasic)
    {
        imageView.setImageResource(R.drawable.holder);

        if(movieBasic!=null && movieBasic.getPosterPath()!=null && !movieBasic.getPosterPath().isEmpty())
        {
            ImageLoader.getInstance().displayImage(baseUrl + movieBasic.getPosterPath(), imageView);
        }
        else if(movieBasic!=null && movieBasic.getBackdropPath()!=null && !movieBasic.getBackdropPath().isEmpty())
        {
            ImageLoader.getInstance().displayImage(baseUrl + movieBasic.getBackdropPath(), imageView);
        }
    }


}
