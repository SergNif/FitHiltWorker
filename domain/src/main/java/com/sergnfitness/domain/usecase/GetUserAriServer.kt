package com.sergnfitness.domain.usecase

import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import javax.inject.Inject

class GetUserAriServer @Inject constructor(
    private val repository: ApiRepository,
) {
    suspend fun invoke(id: Int)
         = repository.getUser(id = id)


}

//suspend fun invoke(id: Int, email: String, password: String): User =
//    repository.getUser(id = id, email = email, password = password)
