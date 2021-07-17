package com.example.marleyspoonassignment.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val NETWORK_TIMEOUT = 30L

val networkModule = module {
    factory { provideHttpLoggingInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { moshi() }
    factory { provideRetrofitBuilder(get(), get()) }

}

fun getBaseUrl(): String {
    return "https://cdn.contentful.com"
}

fun moshi(): Moshi {
    return Moshi.Builder().build()
}

fun provideRetrofitBuilder(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit.Builder {
    val retrofitBuilder = Retrofit.Builder()
    retrofitBuilder.baseUrl(getBaseUrl())
    retrofitBuilder.client(okHttpClient)
    retrofitBuilder .addConverterFactory(MoshiConverterFactory.create(moshi))
    retrofitBuilder  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    return retrofitBuilder
}

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val httpClientBuilder = OkHttpClient.Builder()
    httpClientBuilder.addInterceptor(httpLoggingInterceptor)
    httpClientBuilder.readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
    return httpClientBuilder.build()
}

fun  provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}