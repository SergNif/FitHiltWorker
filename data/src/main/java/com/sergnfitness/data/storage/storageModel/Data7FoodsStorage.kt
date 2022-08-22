package com.sergnfitness.data.storage.storageModel


import java.time.*

data class Data7FoodsStorage (

    var id: Int? = null,
    var egg: Boolean = false,
    var cheese: Boolean = false,
    var nuts: Boolean = false,
    var cottage: Boolean = false,
    var kefir: Boolean = false,
    var yogurt: Boolean = false,
    var date: String = LocalDateTime.now().toString().split("T")[0],
    var fitness_id:Int? = null,
)