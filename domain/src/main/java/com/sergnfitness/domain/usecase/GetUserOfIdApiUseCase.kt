package com.sergnfitness.domain.usecase

import com.sergnfitness.domain.repository.ApiRepository
import javax.inject.Inject

class GetUserOfIdApiUseCase @Inject constructor(
    private val repository: ApiRepository,
) {
    suspend fun invoke(id: Int)
         = repository.getUserOfIdRepos(id = id)
}

//suspend fun invoke(id: Int, email: String, password: String): User =
//    repository.getUser(id = id, email = email, password = password)
