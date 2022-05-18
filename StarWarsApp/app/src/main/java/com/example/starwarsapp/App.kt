package com.example.starwarsapp

import android.app.Application
import com.example.starwarsapp.di.mainModule
import com.example.starwarsapp.di.networkModule
import com.example.starwarsapp.di.repositoryModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    private fun configureDi() {
        startKoin {
            modules(
                listOf(
                    networkModule(BuildConfig.HOST),
                    repositoryModule,
                    mainModule
                )
            )
        }
    }
}