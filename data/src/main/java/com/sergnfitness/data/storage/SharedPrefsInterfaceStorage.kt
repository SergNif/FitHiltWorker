package com.sergnfitness.cleanarchitect.data.storage

import com.sergnfitness.data.storage.storageModel.DataUserStorage
import com.sergnfitness.data.storage.storageModel.UserStorage


interface SharedPrefsInterfaceStorage {
//    fun save(userModelStorage: UserModelStorage):Boolean
//    fun getUserModelStor(): UserModelStorage
//    fun getIdUser():Int
    fun saveUser(user: UserStorage):Boolean
    fun getUser(): UserStorage

    fun saveUserClass(user: UserStorage):Boolean
    fun getUserClass(): MutableList<String>


    fun saveDataUserClass(user: DataUserStorage):Boolean
    fun getDataUserClass(): MutableList<String>
}