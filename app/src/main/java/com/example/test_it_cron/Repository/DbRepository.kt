package com.example.test_it_cron.Repository

import com.example.test_it_cron.Db.AppDatabase
import com.example.test_it_cron.Model.User
import javax.inject.Inject

class DbRepository @Inject constructor(private val db: AppDatabase) {
    fun getUser(login:String) = db.userDao().findByLogin(login)
    fun insertUser(user: User) = db.userDao().insertUser(user)

}