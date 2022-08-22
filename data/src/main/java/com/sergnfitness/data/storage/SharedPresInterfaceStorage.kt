package com.sergnfitness.cleanarchitect.data.storage

import com.sergnfitness.data.storage.storageModel.UserStorage


interface SharedPresInterfaceStorage {
//    fun save(userModelStorage: UserModelStorage):Boolean
//    fun getUserModelStor(): UserModelStorage
//    fun getIdUser():Int
    fun saveUser(user: UserStorage):Boolean
    fun getUser(): UserStorage
}