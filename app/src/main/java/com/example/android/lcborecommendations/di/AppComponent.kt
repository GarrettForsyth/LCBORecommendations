package com.example.android.lcborecommendations.di

import android.app.Application
import com.example.android.lcborecommendations.LCBORecommendationsApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        com.example.android.lcborecommendations.di.MainActivityModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder

        fun build(): AppComponent
    }

    fun inject(app: LCBORecommendationsApp)
}