package com.example.gx.gallerydemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

/**
 * created by gaoxiang on 2020/9/23
 */
class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val _photoListLive = MutableLiveData<List<PhotoItem>>() // MutableLiveData  使用私有修饰， 开放出来的使用非mutable的，比如LiveData

    val photoListLive: LiveData<List<PhotoItem>>    // LiveData是不可变的，保证了外课件，同时外面只能进行获取操作，不能进行修改，（通过get()进行获取）
        get() = _photoListLive

    // 为了简单不单独出一个数据加载类（理论上为了结构清晰、职责明确应该单独出一个类来）
    fun fetchData() {
        val stringRequest = StringRequest(
                Request.Method.GET,
                getUrl(),
                Response.Listener {
                    _photoListLive.value = Gson().fromJson(it, Pixabay::class.java).hits.toList()
                },
                Response.ErrorListener {
                    Log.d("gx", it.toString())
                }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }

    private fun getUrl(): String {
        return "https://pixabay.com/api/?key=12472743-874dc01dadd26dc44e0801d61&q=${keyWords.random()}&per_page=100"
    }

    private val keyWords = arrayOf("cat", "dog", "car", "beauty", "phone", "computer", "flower", "animal")
}