package com.aqif.movieslister.movies.viewmodel;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;

/**
 * Created by aqifhamid on 10/10/17.
 */

public interface IMoviesViewModel extends DatePickerDialog.OnDateSetListener {
    void onActivityCreate();
    void onAcitivityStart();
    void onActivityStop();
    void onActivityDestroy();

    @Override
    void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd);
}
