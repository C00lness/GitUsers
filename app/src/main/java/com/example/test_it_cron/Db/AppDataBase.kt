package com.example.test_it_cron.Db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test_it_cron.Model.User

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}