package com.example.test_it_cron.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.example.test_it_cron.Model.App
import com.example.test_it_cron.Model.User
import com.example.test_it_cron.Network.RetrofitServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GitViewModel(application: Application) : AndroidViewModel(application ) {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    var users: LiveData<List<User>> = _users

    @Inject
    lateinit var retrofitServices: RetrofitServices

    init {
        (application as App).getRetrofitComponent().inject(this)
    }
    @SuppressLint("CheckResult")
    fun getUsers(){
        retrofitServices.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({_users.value = it}, { error -> error.printStackTrace()})
    }
}