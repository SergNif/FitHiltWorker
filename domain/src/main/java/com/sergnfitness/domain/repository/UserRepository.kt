package com.sergnfitness.domain.repository

import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.SaveUserNameParam
import com.sergnfitness.domain.models.user.User

interface UserRepository {
//    fun saveName(saveparam: SaveUserNameParam): Boolean
//    fun getUserSharedPref(): User
//    fun getId(): Int
    fun saveUser(saveParam: User):Boolean
    fun getUser():User

    fun saveDataUser(user: User):Boolean
    fun getDataUser(): MutableList<String>
    fun createExemplarClassDataUserStorageUseRepos(list: MutableList<String>): DataUser
    fun createExemplarClassUserUseRepos(list: MutableList<String>): User
}
