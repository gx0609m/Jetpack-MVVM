package com.example.gx.lifecycles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gx.R;

public class LifecyclesMainActivity extends AppCompatActivity {

    MyChronometer myChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycles_main);
        myChronometer = findViewById(R.id.chronometer);
        // myChronometer作为观察者，观察activity的生命周期
        getLifecycle().addObserver(myChronometer);
    }

}