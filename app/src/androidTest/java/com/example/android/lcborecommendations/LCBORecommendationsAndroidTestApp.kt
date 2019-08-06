package com.example.android.lcborecommendations

import android.app.Activity
import android.app.Application
import com.example.android.core.BuildConfig
import com.example.android.core.di.AppInjector
import com.example.android.core.di.CoreComponent
import com.example.android.core.di.DaggerCoreComponent
import com.example.android.lcborecommendations.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class LCBORecommendationsAndroidTestApp: Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent
            .builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        DaggerAppComponent.builder()
            .application(this)
            .coreComponent(coreComponent)
            .build()
            .inject(this)
        AppInjector.init(this)

        Timber.d("Application onCreate() main source set used.")
    }

    override fun activityInjector() = dispatchingAndroidInjector

}
