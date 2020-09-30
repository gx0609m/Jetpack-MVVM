package com.example.gx.gallerydemo

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * created by gaoxiang on 2020/9/23
 */
class VolleySingleton private constructor(context: Context) {

    // companion object 类似于java中的static
    companion object {
        // 类似于静态变量
        private var INSTANCE: VolleySingleton? = null

        // 类似于静态方法
        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            // 如果INSTANCE为空，则调用私有的构造方法创建，创建的同时赋值（利用also），it 就是 .also 前面的表达式返回的对象
            VolleySingleton(context).also { INSTANCE = it }
        }
        /* 可以去除一对大括号，简化为上面的
        fun getInstance(context: Context) = {
            INSTANCE ?: synchronized(this) {
                // 如果INSTANCE为空，则调用私有的构造方法创建，创建的同时赋值（利用also），it 就是 .also 前面的表达式返回的对象
                VolleySingleton(context).also { INSTANCE = it }
            }
        }*/
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
}