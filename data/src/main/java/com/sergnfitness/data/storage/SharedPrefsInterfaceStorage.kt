package com.sergnfitness.cleanarchitect.data.storage

import com.sergnfitness.data.storage.storageModel.UserStorage


interface SharedPrefsInterfaceStorage {
//    fun save(userModelStorage: UserModelStorage):Boolean
//    fun getUserModelStor(): UserModelStorage
//    fun getIdUser():Int
    fun saveUser(user: UserStorage):Boolean
    fun getUser(): UserStorage

    fun saveDataUser(user: UserStorage):Boolean
    fun getDataUser(): MutableList<String>
}