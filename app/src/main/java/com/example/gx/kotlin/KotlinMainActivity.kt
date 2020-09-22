package com.example.gx.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gx.R
import kotlinx.android.synthetic.main.activity_kotlin_main.*

class KotlinMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        /*// var number: Int = 1; kotlin类型推断，可以简化成如下方式
        var number = 0

        // 使用了kotlin中的 viewBinding，等于是 this.textView16.text = ""
        textView16.text = "0"

        // kotlin支持函数作为参数（java中是匿名内部类）
        buttonPlus.setOnClickListener {
            number++
            textView16.text = number.toString()
        }

        buttonMinus.setOnClickListener {
            textView16.text = (--number).toString()
        }*/


//        val viewModel: KotlinViewModel = ViewModelProviders.of(this).get(KotlinViewModel::class.java)
        val viewModel: KotlinViewModel = ViewModelProvider(this).get(KotlinViewModel::class.java)
        viewModel.number.observe(this, Observer {
            textView16.text = it.toString()
        })
        buttonPlus.setOnClickListener {
            viewModel.modifyNumber(+1)
        }
        buttonMinus.setOnClickListener {
            viewModel.modifyNumber(-1)
        }
    }
}