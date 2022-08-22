package com.sergnfitness.domain.models.user

import java.io.Serializable


data class UsersData constructor(

    var id: Int? = null,
    var userpage: User? = null,
//    val fullName: String? = null,
//    val email: String? = null,
//    val passwordFB: String? = null,
//    val phone: String? = null,
//    val gender: String? = null,
    var data1: Data1ManFeman? = null,
    var data3: Data3AgeHeightWeight? = null,
    var data4: Data4PhysicalActiv? = null,
    var data5: Data5SourceProtein? = null,
    var data6: Data6SourceFiber? = null,
    var data7: Data7Foods? = null,
    var data8: Data8WaterDrink? = null,
    var data9: Data9TypicalDay? = null,
    var data10: Data10BadHabits? = null,

    ): Serializable