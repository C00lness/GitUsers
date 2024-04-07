package com.example.test_it_cron.Di

import com.example.test_it_cron.Network.RetrofitServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {
    val BASE_URL = "https://api.github.com/"

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofitInterface(retrofit: Retrofit): RetrofitServices
    {
        return retrofit.create(RetrofitServices::class.java)
    }
}