package com.example.android.home.di

import androidx.lifecycle.ViewModel
import com.example.android.core.di.ViewModelKey
import com.example.android.home.ui.viewmodels.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel) : ViewModel

}