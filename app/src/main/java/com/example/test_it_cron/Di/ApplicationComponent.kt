package com.example.test_it_cron.Di

import com.example.test_it_cron.View.UserFragment
import com.example.test_it_cron.View.UsersFragment
import com.example.test_it_cron.ViewModel.UsersViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun injectUsers(usersFragment: UsersFragment)
    fun injectUser(userFragment: UserFragment)
}