package com.sergnfitness.domain.models.user



data class User (

    var id: Int? = null,
    var fullName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var fitness_id: Int? = null,
)