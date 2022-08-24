package com.sergnfitness.domain.usecase.putGetUserClass

import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.UserRepository

class SaveAllSharedPreferenceUseCase(
    private val userRepository: UserRepository,
) {
    val TAG = "SaveAllSharedPreferenceUseCase"
    fun execute(param: User): Boolean {
        if (param.fullName.isNullOrBlank()) {
            return false
        } else {
            val result: Boolean = userRepository.saveDataUser(param)
            return result
        }
    }
}