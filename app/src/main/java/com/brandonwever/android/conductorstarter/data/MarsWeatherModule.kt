package com.brandonwever.android.conductorstarter.data

import com.brandonwever.android.conductorstarter.app.ForApplication
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MarsWeatherModule {

    @Provides
    @ForApplication
    fun provideBaseUrl(): String {
        return "http://marsweather.ingenology.com/"
    }

    @Provides
    @ForApplication
    fun provideConverter(): Converter.Factory {
        val gson = Gson()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @ForApplication
    fun provideRetrofit(client: OkHttpClient, baseUrl: String, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(baseUrl).addConverterFactory(converterFactory).build()
    }

    @Provides
    @ForApplication
    fun provideMarsWeatherService(retrofit: Retrofit): MarsWeatherService {
        return retrofit.create(MarsWeatherService::class.java)
    }

    @Provides
    @ForApplication
    fun provideMarsWeatherInteractor(marsWeatherService: MarsWeatherService): MarsWeatherInteractor {
        return MarsWeatherInteractor(marsWeatherService)
    }
}