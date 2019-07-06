package com.example.android.lcborecommendations

import com.example.android.core.AppExecutors
import java.util.concurrent.Executor

/**
 * This classes replaces all the AppExecutors' executors with
 * executors that run instantly. Used for testing.
 */
class InstantAppExecutors : AppExecutors(instant, instant, instant) {
    companion object {
        private val instant = Executor { it.run() }
    }
}
