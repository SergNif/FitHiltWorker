package com.sergnfitness.data.repository

import com.sergnfitness.data.api.ApiServer
import com.sergnfitness.data.extensions.toUser
import com.sergnfitness.data.storage.storageModel.UserStorage
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val api: ApiServer
    ) : ApiRepository {

    override suspend fun getUser(id: Int): Response<User> {
        return api.getEverything(id = id)//, emailQuery = email, passwQuery = password) //.body().toUser()
    }


//    suspend fun getMenu() = api.getHeadLines(5, "28-05-2022" )
}