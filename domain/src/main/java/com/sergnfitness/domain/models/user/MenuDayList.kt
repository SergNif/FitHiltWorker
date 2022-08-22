package com.sergnfitness.domain.models.user


data class MenuDayList(val listMenuDay: List<MenuDay> )

data class MenuDay(
    var id: Int?,
    var id_note:Int,
    var user: String,
    var menu: List<String>,
    var data: String,
    var weight: Double,
)
