package com.example.android.lcborecommendations.di

import com.example.android.lcborecommendations.ui.fragments.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeCuesFragment() : HomeFragment
}