package com.example.android.browse.di

import androidx.lifecycle.ViewModel
import com.example.android.browse.ui.fragments.BrowseFragment
import com.example.android.browse.ui.viewmodels.BrowseViewModel
import com.example.android.core.di.ViewModelFactoryModule
import com.example.android.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class BrowseModule {
    @Binds
    @IntoMap
    @ViewModelKey(BrowseViewModel::class)
    abstract fun bindBrowseViewModel(browseViewModel: BrowseViewModel) : ViewModel
}
