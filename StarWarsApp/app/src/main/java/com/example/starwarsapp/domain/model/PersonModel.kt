package com.example.starwarsapp

import java.io.Serializable

data class PersonModel(
    var name: String?,
    var homeworld: String?
) : Serializable
