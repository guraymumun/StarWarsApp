package com.example.starwarsapp

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory { GetPeopleUseCase(get()) }
    factory { GetPlanetUseCase(get()) }

    viewModel { PeopleViewModel(get()) }
    viewModel { PlanetViewModel(get()) }
}