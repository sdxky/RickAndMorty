package com.example.rickandmorty.data.di

import com.example.rickandmorty.data.repository.Repository
import com.example.rickandmorty.data.apiService.ApiService
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.factory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://rickandmortyapi.com/api/"

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideApiService(get()) }
    single { provideRepository(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .writeTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun provideRepository(apiService: ApiService): Repository {
    return Repository(apiService)
}


