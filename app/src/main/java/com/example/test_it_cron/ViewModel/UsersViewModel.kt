package com.example.test_it_cron.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.example.test_it_cron.Model.App
import com.example.test_it_cron.Model.Users
import com.example.test_it_cron.Network.RetrofitServices
import com.example.test_it_cron.Repository.UsersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UsersViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    private val _users = MutableLiveData<List<Users>>()
    val users: LiveData<List<Users>>
    get() = _users
    private var disposable: Disposable? = null

    fun getUsers(){
        disposable = usersRepository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({_users.value = it}, { error -> error.printStackTrace()})
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}

class UsersViewModelFactory @Inject constructor(private val usersRepository: UsersRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(usersRepository) as T
    }
}