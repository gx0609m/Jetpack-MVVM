package com.example.gx.lifecycles;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 实现 LifecycleObserver，作为观察者观察 Activity的生命周期
 * <p>
 * created by gaoxiang on 2020/9/21
 */
public class MyChronometer extends Chronometer implements LifecycleObserver {

    private long elapsedTime;

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void pauseMeter() {
        elapsedTime = SystemClock.elapsedRealtime() - getBase();
        stop();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void resumeMeter() {
        setBase(SystemClock.elapsedRealtime() - elapsedTime);
        start();
    }
}
