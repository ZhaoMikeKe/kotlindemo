package com.huachu.kotlindemo.request

import com.huachu.kotlindemo.bean.FollowersEntity
import com.huachu.kotlindemo.bean.UserInfoEntity
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {
    @GET("users/{path}")
    fun getUserInfo(@Path("path") string: String): Call<UserInfoEntity>

    @GET("users/ZhaoMikeKe/followers")
    fun getFollowers(): Observable<List<FollowersEntity>>
}
