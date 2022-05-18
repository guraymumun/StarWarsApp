package com.example.starwarsapp.data.remote.dto

data class PeopleResponseDTO(
    var count: Int?,
    var next: String?,
    var previous: String?,
    var results: List<PersonDTO>?
)

data class PersonDTO(
    var name: String?,
    var homeworld: String?
)