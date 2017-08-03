package com.example.archdemo.arch;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.widget.Toast;

import com.example.archdemo.ArchDemoApp;

/**
 * Created by baurine on 8/3/17.
 */

public class MusicPlayer implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Toast.makeText(ArchDemoApp.getInstance(), "Start Play...", Toast.LENGTH_LONG).show();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Toast.makeText(ArchDemoApp.getInstance(), "Pause Play...", Toast.LENGTH_LONG).show();
    }
}
