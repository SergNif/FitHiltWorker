package com.sergnfitness.android.fit.presentation.login

import androidx.lifecycle.ViewModel
import com.sergnfitness.domain.usecase.GetUserSharedPreferenceUseCase
import com.sergnfitness.domain.usecase.SaveUserSharedPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val getUserSharedPreferenceUseCase: GetUserSharedPreferenceUseCase,
    private val saveUserSharedPreferenceUseCase: SaveUserSharedPreferenceUseCase,
) : ViewModel() {


    fun isNewUser(): Boolean {
        return getUserSharedPreferenceUseCase.execute().id == 85000
    }
}