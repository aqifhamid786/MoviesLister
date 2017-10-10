package com.aqif.movieslister.movies.recycler;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by aqifhamid on 10/9/17.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder
{
    private ViewDataBinding mDataBinding;

    public MovieViewHolder(ViewDataBinding dataBindind)
    {
        super(dataBindind.getRoot());
        mDataBinding = dataBindind;
        mDataBinding.executePendingBindings();
    }

    public ViewDataBinding getViewDataBinding()
        {
            return mDataBinding;
        }

}
