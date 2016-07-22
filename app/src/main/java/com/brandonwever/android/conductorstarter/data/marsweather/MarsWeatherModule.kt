package com.brandonwever.android.conductorstarter.data.marsweather

import auto.parcelgson.gson.AutoParcelGsonTypeAdapterFactory
import com.brandonwever.android.conductorstarter.data.typeadapters.LocalDateTypeAdapter
import com.brandonwever.android.conductorstarter.data.typeadapters.LocalTimeTypeAdapter
import com.brandonwever.android.conductorstarter.data.typeadapters.ZonedDateTimeTypeAdapter
import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.ZonedDateTime
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
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
    fun provideConverter(): Factory {
        val gson = GsonBuilder()
                .registerTypeAdapterFactory(AutoParcelGsonTypeAdapterFactory())
                .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeTypeAdapter())
                .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
                .registerTypeAdapter(LocalTime::class.java, LocalTimeTypeAdapter())
                .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
                .create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, baseUrl: String, converterFactory: Factory): Retrofit {
        return Builder()
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

