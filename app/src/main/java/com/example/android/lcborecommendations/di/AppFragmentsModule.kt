package com.example.android.lcborecommendations.di

import com.example.android.browse.di.BrowseModule
import com.example.android.browse.ui.fragments.BrowseFragment
import com.example.android.core.di.scope.FeatureScope
import com.example.android.home.di.HomeModule
import com.example.android.home.ui.fragments.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppFragmentsModule {
    @FeatureScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeHomeFragment() : HomeFragment

    @FeatureScope
    @ContributesAndroidInjector(modules = [BrowseModule::class])
    abstract fun contributeBrowseFragment() : BrowseFragment
}