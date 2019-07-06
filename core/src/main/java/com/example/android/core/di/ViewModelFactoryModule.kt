package com.example.android.core.di

import androidx.lifecycle.ViewModelProvider
import com.example.android.core.ui.LcboRecommendationsViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: LcboRecommendationsViewModelFactory) : ViewModelProvider.Factory
}


