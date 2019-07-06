package com.example.android.core.di

import dagger.Component
import javax.inject.Singleton

/**
 * Component providing application wide singletons.
 */
//@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    @Component.Builder interface Builder {
        fun build(): CoreComponent
    }
}
