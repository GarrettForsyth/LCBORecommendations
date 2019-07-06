package com.example.android.lcborecommendations.di

import com.example.android.browse.di.BrowseModule
import com.example.android.home.di.HomeModule
import com.example.android.lcborecommendations.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [AppFragmentsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}