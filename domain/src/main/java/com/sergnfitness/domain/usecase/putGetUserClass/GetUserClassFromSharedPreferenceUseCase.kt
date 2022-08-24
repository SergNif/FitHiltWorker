package com.sergnfitness.domain.usecase.putGetUserClass

import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.UserRepository

class GetUserClassFromSharedPreferenceUseCase(
    private val userRepository: UserRepository,
) {
    val TAG = "GetUserClassFromSharedPreferenceUseCase"
    fun execute(): MutableList<String> {
        return userRepository.getDataUser()
    }
}