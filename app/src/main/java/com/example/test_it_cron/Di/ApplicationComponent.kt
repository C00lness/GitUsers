package com.example.test_it_cron.Di

import android.content.Context
import com.example.test_it_cron.Repository.DbRepository
import com.example.test_it_cron.View.UserFragment
import com.example.test_it_cron.View.UsersFragment
import com.example.test_it_cron.ViewModel.UserViewModel
import com.example.test_it_cron.ViewModel.UsersViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DbModule::class])
interface ApplicationComponent {
    fun injectUsers(usersFragment: UsersFragment)
    fun injectUser(userFragment: UserFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context):ApplicationComponent
    }
}