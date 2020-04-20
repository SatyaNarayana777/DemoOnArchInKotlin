package com.kotlin.demoinkotlin.di

import com.kotlin.demoinkotlin.BuildConfig
import com.kotlin.demoinkotlin.network.ApiInterface
import com.kotlin.demoinkotlin.utils.BASE_URL
import com.kotlin.demoinkotlin.utils.BASE_URL_2
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@Suppress("unused")
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpInterceptors(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    @Singleton
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
                //.addInterceptor(AuthInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(60.toLong(), TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    @Named("Retrofit_1")
    internal fun provideRetrofitInterface(@NonNull okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    @Singleton
    @Named("Retrofit_2")
    internal fun provideNewRetrofitInterface(@NonNull okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_2)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    @Singleton
    @Named("Api_1")
    internal fun provideApiInterface(@Named("Retrofit_1") retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    @Named("Api_2")
    internal fun provideNewApiInterface(@Named("Retrofit_2") retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

}
