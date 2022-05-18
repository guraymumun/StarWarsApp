package com.example.starwarsapp.domain.model

import java.io.Serializable

data class PersonModel(
    var name: String?,
    var homeworld: String?
) : Serializable
