package com.example.test_it_cron.Repository

import com.example.test_it_cron.Network.RetrofitServices
import javax.inject.Inject

class UsersRepository @Inject constructor(private val retrofitServices: RetrofitServices) {
    fun getUsers() = retrofitServices.getUsers()
}