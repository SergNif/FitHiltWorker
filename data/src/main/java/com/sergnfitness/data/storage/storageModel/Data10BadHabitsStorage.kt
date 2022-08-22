package com.sergnfitness.data.storage.storageModel

import java.time.LocalDateTime


data class Data10BadHabitsStorage(
    var id: Int? = null,
    var fastFood: Boolean = true,
    var laterNight: Boolean = false,
    var fastSugar: Boolean = false,
    var Nothing: Boolean = false,
    var date: String = LocalDateTime.now().toString().split("T")[0],
    var fitness_id: Int? = null,
)

