package com.example.gx.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel 可以防止 在横竖屏切换，语言切换 以及 界面被系统杀死 这些情况下对数据的保存维护
 * <p>
 * created by gaoxiang on 2020/9/21
 */
public class MyViewModel2 extends ViewModel {

    private static final String KEY = "number";

    private SavedStateHandle handle;

    public MyViewModel2(SavedStateHandle handle) {
        if (!handle.contains(KEY)) {
            handle.set(KEY, 0);
        }
        this.handle = handle;
    }

    public LiveData<Integer> getNumber() {
        return handle.getLiveData(KEY);
    }

    public void add() {
        handle.set(KEY, (int) handle.get(KEY) + 1);
    }
}
