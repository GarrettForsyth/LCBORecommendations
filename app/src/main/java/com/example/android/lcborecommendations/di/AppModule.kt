package com.example.android.lcborecommendations.di

import com.example.android.core.AppExecutors
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideAppExecutors() = AppExecutors()
}