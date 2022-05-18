package com.example.starwarsapp

import com.example.starwarsapp.model.network.services.PeopleService
import com.example.starwarsapp.model.network.services.PlanetService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun networkModule(host: String) = module {
    factory(named("httpLoggingInterceptor")) {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>(named("httpLoggingInterceptor")))
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    factory {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLenient()
            .create()
    }


    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(host)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    factory { get<Retrofit>().create(PeopleService::class.java) }
    factory { PeopleDataSource(get()) }

    factory { get<Retrofit>().create(PlanetService::class.java) }
    factory { PlanetDataSource(get()) }
}