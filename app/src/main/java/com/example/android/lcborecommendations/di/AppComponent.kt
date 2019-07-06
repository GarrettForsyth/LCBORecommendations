package com.example.android.lcborecommendations.di

import android.app.Application
import com.example.android.core.di.CoreComponent
import com.example.android.core.di.ViewModelFactoryModule
import com.example.android.lcborecommendations.LCBORecommendationsApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        ViewModelFactoryModule::class
    ],
    dependencies = [CoreComponent::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AppComponent
    }

    fun inject(app: LCBORecommendationsApp)
}