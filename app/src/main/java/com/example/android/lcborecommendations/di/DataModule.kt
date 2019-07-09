package com.example.android.lcborecommendations.di

import android.app.Application
import androidx.room.Room
import com.example.android.core.AppExecutors
import com.example.android.core.data.LCBODatabase
import com.example.android.core.data.SupportSQLiteQueryConverter
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    lateinit var database: LCBODatabase

    @Provides
    fun provideDb(
        app: Application,
        appExecutors: AppExecutors
    ): LCBODatabase {
        database = Room.databaseBuilder(
                app,
                LCBODatabase::class.java,
                "lcbo_recommendations.db"
            )
            .fallbackToDestructiveMigration() //todo pick a nondestructive migration
            .build()
        return database
    }

    @Provides
    fun provideSupportSQLiteQueryConverter() = SupportSQLiteQueryConverter()

    @Provides
    fun provideLCBOItemDao(db: LCBODatabase) = db.lcboItemDao()

}