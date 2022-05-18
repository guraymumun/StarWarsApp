package com.example.starwarsapp.di

import com.example.starwarsapp.domain.usecase.GetNextPeopleUseCase
import com.example.starwarsapp.domain.usecase.GetPlanetUseCase
import com.example.starwarsapp.presentation.viewmodel.PeopleViewModel
import com.example.starwarsapp.presentation.viewmodel.PlanetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory { GetNextPeopleUseCase(get()) }
    factory { GetPlanetUseCase(get()) }

    viewModel { PeopleViewModel(get()) }
    viewModel { PlanetViewModel(get()) }
}