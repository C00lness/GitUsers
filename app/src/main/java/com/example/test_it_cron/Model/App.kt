package com.example.test_it_cron.Model

import android.app.Application
import com.example.test_it_cron.Di.ApplicationComponent
import com.example.test_it_cron.Di.DaggerApplicationComponent
import com.example.test_it_cron.Di.NetworkModule

class App: Application() {
    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent
            .builder()
            .networkModule(NetworkModule()).build()
    }

    fun getComponent(): ApplicationComponent = component
}