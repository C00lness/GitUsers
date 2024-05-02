package com.example.test_it_cron.Di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.test_it_cron.Db.AppDatabase
import com.example.test_it_cron.Network.RetrofitServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun getDataBaseInstance(context: Context): AppDatabase
    {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "user.db"
        ).build()
    }
}