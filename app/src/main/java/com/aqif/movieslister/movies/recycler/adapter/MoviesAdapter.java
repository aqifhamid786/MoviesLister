package com.aqif.movieslister.movies.recycler.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aqif.movieslister.BR;
import com.aqif.movieslister.R;
import com.aqif.movieslister.movies.handlers.MovieInputHandler;
import com.aqif.movieslister.movies.recycler.MovieViewHolder;
import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MovieBasic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aqifhamid on 10/9/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> implements IMoviesAdapter
{

    private static final int MOVIE_CARD = 0;
    private static final int LOADING_CARD = 1;

    private MovieInputHandler mMovieInputHandler;

    private List<MovieBasic> mMovies;
    private boolean mLoadingMoreItem;

    public MoviesAdapter()
    {
        mMovies = new ArrayList<>();
        mMovieInputHandler = new MovieInputHandler();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding = null;
        switch(viewType)
        {
            case MOVIE_CARD:
                viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.movie_list_item, parent, false);
                break;
            case LOADING_CARD:
                viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.loading_view_card, parent, false);
                break;
        }
        return new MovieViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position)
    {
        if(position< mMovies.size()) {
            holder.getViewDataBinding().setVariable(BR.movie, mMovies.get(position));
            holder.getViewDataBinding().setVariable(BR.handler, mMovieInputHandler);
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        return position == mMovies.size() ? LOADING_CARD : MOVIE_CARD;
    }

    @Override
    public int getItemCount()
    {
        return mLoadingMoreItem ? mMovies.size()+1 : mMovies.size();
    }


    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public void setLoadingMoreItem(boolean loadingMoreItem)
    {

        mLoadingMoreItem = loadingMoreItem;
        if(mLoadingMoreItem)
            notifyItemInserted(getItemCount());
        else
            notifyItemRemoved(getItemCount());
    }

    @Override
    public void updateData(List<MovieBasic> movies)
    {
        while(mMovies.size()>movies.size())
        {
            int removeIndex = mMovies.size()-1;
            notifyItemRemoved(removeIndex);
            mMovies.remove(removeIndex);
        }

        for(int lop=0; lop<mMovies.size() && lop<movies.size(); lop++)
        {
            mMovies.set(lop, movies.get(lop));
            notifyItemChanged(lop);
        }

        while(mMovies.size()<movies.size())
        {
            mMovies.add(movies.get(mMovies.size()));
            notifyItemInserted(mMovies.size()-1);
        }
    }

}

