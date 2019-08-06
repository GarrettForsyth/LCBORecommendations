package com.example.android.lcborecommendations.di

import androidx.test.core.app.ApplicationProvider
import com.example.android.lcborecommendations.LCBORecommendationsAndroidTestApp

object {

    fun injectBrowseLCBOItemsTestConfiguration() {
        val androidTestApp = ApplicationProvider.getApplicationContext<LCBORecommendationsAndroidTestApp>()
        DaggerAndroidTestAppComponent.builder()
            .application
            .build()
    }
}