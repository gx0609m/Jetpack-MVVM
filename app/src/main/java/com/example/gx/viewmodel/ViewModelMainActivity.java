package com.example.gx.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.gx.R;
import com.example.gx.databinding.ActivityViewModelMainBinding;

public class ViewModelMainActivity extends AppCompatActivity {

    MyViewModel2 myViewModel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityViewModelMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model_main);
        myViewModel2 = ViewModelProviders.of(this, new SavedStateViewModelFactory(getApplication(), this)).get(MyViewModel2.class);
        binding.setData2(myViewModel2);
        binding.setLifecycleOwner(this);
    }

}