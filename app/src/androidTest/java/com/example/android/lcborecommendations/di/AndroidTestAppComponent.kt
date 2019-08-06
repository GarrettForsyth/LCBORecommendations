package com.example.android.lcborecommendations.di

import android.app.Application
import com.example.android.core.di.CoreComponent
import com.example.android.core.di.ViewModelFactoryModule
import com.example.android.lcborecommendations.LCBORecommendationsAndroidTestApp
import com.example.android.lcborecommendations.LCBORecommendationsApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * The modules to inject for android instrumentation tests.
 *
 * In particular, the network module is swapped out with an
 * implementation using a mock server so tests don't rely on
 * states external to the app.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        DataModule::class,
        AndroidTestNetworkModule::class,
        ViewModelFactoryModule::class
    ],
    dependencies = [CoreComponent::class]
)
interface AndroidTestAppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AndroidTestAppComponent
    }

    fun inject(app: LCBORecommendationsAndroidTestApp)
}
