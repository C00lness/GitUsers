package com.example.test_it_cron.Network

import io.reactivex.Observable
import com.example.test_it_cron.Model.User
import com.example.test_it_cron.Model.UserDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServices {
    @GET("users")
    fun getUsers(): Observable<List<User>>

    @GET("users/{user}")
    fun getUserDetail(@Path("user") user: String): Observable<UserDetails>
}