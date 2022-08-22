package com.sergnfitness.domain.usecase

import android.util.Log
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import com.sergnfitness.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: ApiRepository
){
    operator fun invoke(): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())

            val coins = repository.getUser(id = 5)

            emit(Resource.Succes(coins.body()))

        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        }
    }
}