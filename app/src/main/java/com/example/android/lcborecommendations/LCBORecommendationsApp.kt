package com.example.android.lcborecommendations

import android.app.Application
import com.example.android.core.di.AppInjector
import com.example.android.lcborecommendations.di.DaggerAppComponent
import timber.log.Timber

class LCBORecommendationsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        AppInjector.init(this)

        Timber.d("Application onCreate() main source set used.")
    }

}