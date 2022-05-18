package com.example.starwarsapp

import org.koin.dsl.module

val repositoryModule = module {
    factory<PeopleRepository> { PeopleRepositoryImpl(get()) }
    factory<PlanetRepository> { PlanetRepositoryImpl(get()) }
}