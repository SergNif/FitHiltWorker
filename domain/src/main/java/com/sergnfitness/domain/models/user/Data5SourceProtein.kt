package com.sergnfitness.domain.models.user



import java.time.LocalDateTime
import java.util.*

data class Data5SourceProtein(

    var id: Int? = null,
    var chicken: Boolean = false,
    var tyrkey: Boolean = false,
    var pork: Boolean = false,
    var meat: Boolean = false,
    var fish: Boolean = false,
    var seaFood: Boolean = false,
    var withoutMeat: Boolean = false,
    var withoutFish: Boolean = false,
    var date: String = LocalDateTime.now().toString().split("T")[0],
    var fitness_id:Int? = null,
)