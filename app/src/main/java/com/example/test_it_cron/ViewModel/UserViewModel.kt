package com.example.test_it_cron.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import android.telecom.Call.Details
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_it_cron.Model.App
import com.example.test_it_cron.Model.User
import com.example.test_it_cron.Model.UserDetails
import com.example.test_it_cron.Network.RetrofitServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserViewModel (application: Application) : AndroidViewModel(application ) {

    private val _details: MutableLiveData<UserDetails> = MutableLiveData()
    var details: LiveData<UserDetails> = _details

    @Inject
    lateinit var retrofitServices: RetrofitServices

    init {
        (application as App).getRetrofitComponent().injectDetails(this)
    }
    @SuppressLint("CheckResult")
    fun getDetails(id: String){
        retrofitServices.getUserDetail(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({_details.value = it}, { error -> error.printStackTrace()})
    }
}