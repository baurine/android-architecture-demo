package com.example.archdemo.arch;

import android.arch.lifecycle.ViewModel;

import java.util.Date;

/**
 * Created by baurine on 8/3/17.
 */

public class DateViewModel extends ViewModel {
    private Date date;

    public Date getDate() {
        if (date == null) {
            date = new Date();
        }
        return date;
    }
}
