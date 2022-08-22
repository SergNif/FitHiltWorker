package com.sergnfitness.data.storage.storageModel

import com.sergnfitness.data.storage.storageModel.*
import com.sergnfitness.domain.models.user.*
import java.io.Serializable

data class UsersDataStorage constructor (

    var id: Int? = null,
    var userpage: User? = null,
//    val fullName: String? = null,
//    val email: String? = null,
//    val passwordFB: String? = null,
//    val phone: String? = null,
//    val gender: String? = null,
    var data1: Data1ManFemanStorage? = null,
    var data3: Data3AgeHeightWeightStorage? = null,
    var data4: Data4PhysicalActivStorage? = null,
    var data5: Data5SourceProteinStorage? = null,
    var data6: Data6SourceFiberStorage? = null,
    var data7: Data7FoodsStorage? = null,
    var data8: Data8WaterDrinkStorage? = null,
    var data9: Data9TypicalDayStorage? = null,
    var data10: Data10BadHabitsStorage? = null,

    ): Serializable