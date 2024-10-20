package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.data.module.appModule
import com.example.rickandmorty.data.module.networkModule
import com.example.rickandmorty.data.module.repositoryModule
import com.example.rickandmorty.data.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(Level.INFO)
            modules(appModule)
        }
    }
}