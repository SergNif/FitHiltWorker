package com.sergnfitness.domain.repository

import com.sergnfitness.domain.models.user.User
import retrofit2.Response


interface ApiRepository {
    suspend fun getUser(id:Int) : Response<User>
}