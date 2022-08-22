package com.sergnfitness.domain.usecase

import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.UserRepository

class SaveUserSharedPreferenceUseCase(private val userRepository: UserRepository) {
    val TAG = "SaveUserSharedPreference"
    fun execute(param: User): Boolean {
        if (param.fullName.isNullOrBlank()) {
            return false
        } else {
            val result: Boolean = userRepository.saveUser(saveParam = param)
            return result
        }
    }
}