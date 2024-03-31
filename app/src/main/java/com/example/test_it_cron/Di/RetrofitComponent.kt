package com.example.test_it_cron.Di

import com.example.test_it_cron.ViewModel.GitViewModel
import com.example.test_it_cron.ViewModel.UserViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {
    fun inject(viewModel: GitViewModel)
    fun injectDetails(viewModel: UserViewModel)
}