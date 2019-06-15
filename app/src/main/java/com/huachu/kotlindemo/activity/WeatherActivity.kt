package com.huachu.kotlindemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.huachu.kotlindemo.R
import com.huachu.kotlindemo.adapter.ForecastListAdapter
import com.huachu.kotlindemo.bean.UserInfoEntity
import com.huachu.kotlindemo.request.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.measureTimeMillis

class WeatherActivity : AppCompatActivity() {
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        val forecastList = findViewById(R.id.forecast_list) as RecyclerView
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)

        val baseUrl: String = "https://api.github.com/"
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(Api::class.java)

        api.getUserInfo("ZhaoMikeKe")//普通请求
                .enqueue(object : Callback<UserInfoEntity> {
                    override fun onFailure(call: Call<UserInfoEntity>?, t: Throwable?) {
                        println(t?.localizedMessage)
                    }

                    override fun onResponse(call: Call<UserInfoEntity>?, response: Response<UserInfoEntity>?) {
                        val userInfoEntity = response?.body()//获取请求数据实体
                        println(userInfoEntity.toString())//打印请求结果
                    }
                })

        api.getFollowers()//rxjva
                .subscribeOn(Schedulers.io())//切换io线程请求网络
                .unsubscribeOn(AndroidSchedulers.mainThread())//切换到ui主线程更新ui
                .subscribe({ result ->
                    //请求结果
                    println(result.toString())
                }, { error ->
                    //请求错误
                    println(error.message)
                }, {
                    //请求完成
                    println("complete")
                })

        postItem()//协程
        postItem1()
    }

    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // 假设我们在这里做了一些有用的事
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // 假设我们在这里也做了一些有用的事
        return 29
    }

    fun postItem() {
        GlobalScope.launch {
            val time = measureTimeMillis {

                val one = doSomethingUsefulOne()
                val two = doSomethingUsefulTwo()
                Log.d("zhooo", "The answer is ${one + two}")
            }
            Log.d("zhooo", "Completed in $time ms")

        }

    }

    fun postItem1() {
        GlobalScope.launch {
            val time = measureTimeMillis {
                val one = async { doSomethingUsefulOne() }.await()
                val two = async { doSomethingUsefulTwo() }.await()
                Log.d("zhooo", "The answer is ${one + two}")
            }
            Log.d("zhooo", "Completed in $time ms")
        }
    }
}
