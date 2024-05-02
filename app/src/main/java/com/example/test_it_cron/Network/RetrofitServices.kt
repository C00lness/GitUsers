package com.example.test_it_cron.Network

import com.example.test_it_cron.Model.User
import com.example.test_it_cron.Model.Users
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServices {
    @GET("users")
    fun getUsers(): Single<List<Users>>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Single<User>
}