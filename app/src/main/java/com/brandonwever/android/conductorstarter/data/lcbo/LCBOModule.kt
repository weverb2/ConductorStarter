package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.lcbo.interactor.LCBOInteractor
import com.brandonwever.android.conductorstarter.data.lcbo.interactor.RealLCBOInteractor
import com.brandonwever.android.conductorstarter.data.redux.Action
import com.brandonwever.android.conductorstarter.data.redux.AppState
import com.brandonwever.android.conductorstarter.data.redux.RxStore
import com.brandonwever.android.conductorstarter.data.typeadapters.LocalDateTypeAdapter
import com.brandonwever.android.conductorstarter.data.typeadapters.LocalTimeTypeAdapter
import com.brandonwever.android.conductorstarter.data.typeadapters.ZonedDateTimeTypeAdapter
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.ZonedDateTime
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Named
import javax.inject.Singleton

@Module
class LCBOModule {

    @Provides
    @Named("baseUrl")
    @Singleton
    fun provideBaseUrl(): String {
        return "http://lcboapi.com/"
    }

    @Provides
    @Named("accessKey")
    @Singleton
    fun provideAccessKey(): String {
        return "MDo2YTQ2OTU2YS02ZTMwLTExZTYtOTdiOC1hZjFlNjUxY2FkNDY6eWprTjFtZzBmODlKNTdxOERmcWx6d09QT1hQS0FBREN2dnE2";
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(@Named("accessKey") accessKey: String): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val accessRequest = originalRequest.newBuilder().addHeader("Authorization", accessKey).build()
            Timber.d(accessRequest.toString())
            chain.proceed(accessRequest)
        }
    }

    @Provides
    @Named("LCBO")
    @Singleton
    fun provideOkHttpClient(cache: Cache, interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().cache(cache).addInterceptor(interceptor).build()
    }

    @Provides
    @Named("LCBO")
    @Singleton
    fun provideConverter(): Converter.Factory {
        val gson = GsonBuilder()
                .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeTypeAdapter())
                .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
                .registerTypeAdapter(LocalTime::class.java, LocalTimeTypeAdapter())
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Named("LCBO")
    @Singleton
    fun provideRetrofit(@Named("LCBO") client: OkHttpClient, @Named("baseUrl") baseUrl: String, @Named("LCBO") converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    @Singleton
    fun provideLCBOService(@Named("LCBO") retrofit: Retrofit): LCBOService {
        return retrofit.create(LCBOService::class.java)
    }

    @Provides
    @Singleton
    fun provideLCBOInteractor(service: LCBOService): LCBOInteractor {
        return RealLCBOInteractor(service)
    }

    @Provides
    @Singleton
    fun provideLCBOActionCreator(interactor: LCBOInteractor, store: RxStore<AppState, Action>): LCBOActionCreator {
        return LCBOActionCreator(interactor, store)
    }
}
