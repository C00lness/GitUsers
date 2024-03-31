package com.example.test_it_cron.Model

import android.app.Application
import com.example.test_it_cron.Di.DaggerRetrofitComponent
import com.example.test_it_cron.Di.RetrofitComponent
import com.example.test_it_cron.Di.RetrofitModule

class App: Application() {
    private lateinit var rComponent: RetrofitComponent

    override fun onCreate() {
        super.onCreate()
        rComponent = DaggerRetrofitComponent
            .builder()
            .retrofitModule(RetrofitModule()).build()
    }

    fun getRetrofitComponent(): RetrofitComponent = rComponent
}