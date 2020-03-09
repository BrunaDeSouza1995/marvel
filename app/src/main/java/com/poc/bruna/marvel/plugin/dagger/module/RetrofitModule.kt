package com.poc.bruna.marvel.plugin.dagger.module

import com.poc.bruna.marvel.BuildConfig.MARVEL_BASE_URL
import com.poc.bruna.marvel.plugin.retrofit.MarvelService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 60L

@Module
class RetrofitModule {

    @Provides
    fun getMarvelService(okHttpClient: OkHttpClient): MarvelService {
        return Retrofit.Builder()
            .baseUrl(MARVEL_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelService::class.java)
    }

    @Provides
    fun getOkHttpClient(httppLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httppLoggingInterceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}