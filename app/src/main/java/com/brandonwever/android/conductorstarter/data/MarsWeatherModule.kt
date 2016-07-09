package com.brandonwever.android.conductorstarter.data

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MarsWeatherModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return "http://marsweather.ingenology.com/"
    }

    @Provides
    @Singleton
    fun provideConverter(): Converter.Factory {
        val gson = Gson()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, baseUrl: String, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(baseUrl).addConverterFactory(converterFactory).build()
    }

    @Provides
    @Singleton
    fun provideMarsWeatherService(retrofit: Retrofit): MarsWeatherService {
        return retrofit.create(MarsWeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideMarsWeatherInteractor(marsWeatherService: MarsWeatherService): MarsWeatherInteractor {
        return MarsWeatherInteractor(marsWeatherService)
    }
}