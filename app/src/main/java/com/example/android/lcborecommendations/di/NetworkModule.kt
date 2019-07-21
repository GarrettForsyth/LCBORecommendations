package com.example.android.lcborecommendations.di

import com.example.android.browse.api.LCBOApiService
import com.example.android.browse.api.LiveDataCallAdapterFactory
import com.example.android.core.api.EnvelopeConverterFactory
import com.example.android.core.api.QueryOptionsMapConverter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideLCBOApiService(): LCBOApiService {
        return Retrofit.Builder()
            .baseUrl("https://localhost:3000") //todo deploy
            .addConverterFactory(EnvelopeConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(LCBOApiService::class.java)
    }

    @Provides
    fun provideQueryOptionsMapConverter() = QueryOptionsMapConverter()

}