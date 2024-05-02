package com.example.test_it_cron.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.test_it_cron.Di.ApplicationComponent
import com.example.test_it_cron.Di.DaggerApplicationComponent
import com.example.test_it_cron.Model.User
import com.example.test_it_cron.Repository.DbRepository
import com.example.test_it_cron.Repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserViewModel (private val userRepository: UserRepository, private val dbRepository: DbRepository) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
    get() = _user
    private var disposable: Disposable? = null
    fun getUser(id: String){
        disposable = dbRepository.getUser(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    _user.value = it
                    Log.d("Mode", "Db")
                },
                {
                    disposable = userRepository.getUser(id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe (
                            {
                                _user.value = it
                                Log.d("Mode", "Net")
                                dbRepository.insertUser(it).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
                            },
                            {
                                error -> error.printStackTrace()
                            })

                })
    }
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}

class UserViewModelFactory @Inject constructor(private val userRepository: UserRepository, private val dbRepository: DbRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository, dbRepository) as T
    }
}