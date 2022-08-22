package com.sergnfitness.domain.models.user



import java.time.LocalDateTime
import java.util.*

data class Data8WaterDrink(

    var id: Int? = null,
    var waterWithoutGas:Boolean=true,
    var waterSugarGas:Boolean=false,
    var coffee:Boolean=false,
    var tea:Boolean=false,
    var date: String = LocalDateTime.now().toString().split("T")[0],
    var fitness_id:Int? = null,
) {
//    init {
//        if (waterWithoutGas) {
//            waterSugarGas = false
//            coffee = false
//            tea = false
//        }
//
//        if (waterSugarGas) {
//            waterWithoutGas = false
//            coffee = false
//            tea = false
//        }
//
//        if (coffee) {
//            waterWithoutGas = false
//            waterSugarGas = false
//            tea = false
//        }
//
//        if (tea) {
//            waterWithoutGas = false
//            waterSugarGas = false
//            coffee = false
//        }
//
//    }
}