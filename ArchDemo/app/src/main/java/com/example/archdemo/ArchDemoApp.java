package com.example.archdemo;

import android.app.Application;

/**
 * Created by baurine on 8/3/17.
 */

public class ArchDemoApp extends Application {
    public static ArchDemoApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        ArchDemoApp.instance = this;
    }

    public static ArchDemoApp getInstance() {
        return instance;
    }
}
