package com.example.gx.kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * created by gaoxiang on 2020/9/22
 */
class KotlinViewModel : ViewModel() {

    private val _number: MutableLiveData<Int> by lazy { MutableLiveData<Int>().also { it.value = 0 } }
    val number: LiveData<Int>
        get() = _number
//    val number = MutableLiveData(0) // 不是lazy了

    fun modifyNumber(aNumber: Int) {
        _number.value = _number.value?.plus(aNumber)
    }

}