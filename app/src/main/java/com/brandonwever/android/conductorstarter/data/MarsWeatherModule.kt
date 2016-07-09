package com.brandonwever.android.conductorstarter.data

import auto.parcelgson.gson.AutoParcelGsonTypeAdapterFactory
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
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
    val gson = GsonBuilder().registerTypeAdapterFactory(
        AutoParcelGsonTypeAdapterFactory()).setFieldNamingPolicy(
        FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    return GsonConverterFactory.create(gson)
  }

  @Provides
  @Singleton
  fun provideRetrofit(client: OkHttpClient, baseUrl: String,
      converterFactory: Converter.Factory): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
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