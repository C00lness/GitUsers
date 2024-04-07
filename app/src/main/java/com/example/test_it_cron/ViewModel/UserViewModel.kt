package com.example.test_it_cron.ViewModel

import androidx.lifecycle.*
import com.example.test_it_cron.Model.User
import com.example.test_it_cron.Repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserViewModel (private val userRepository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
    get() = _user
    var disposable: Disposable? = null

    fun getUser(id: String){
        disposable = userRepository.getUser(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({_user.value = it}, { error -> error.printStackTrace()})
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}

class UserViewModelFactory @Inject constructor(private val userRepository: UserRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}