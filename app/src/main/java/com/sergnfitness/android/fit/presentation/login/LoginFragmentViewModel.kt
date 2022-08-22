package com.sergnfitness.android.fit.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergnfitness.android.model.CoinListState
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.usecase.*
import com.sergnfitness.domain.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragmentViewModel @Inject constructor(
    private val gGetUserOfEmailPasswordApiUseCase: GetUserOfEmailPasswordApiUseCase,
    private val getUserOfIdApiUseCase: GetUserOfIdApiUseCase,
//    private val NOTuSEsGetUserOfIdApiUseCase: NOTuSEsGetUserOfIdApiUseCase,
    private val getUserSharedPreferenceUseCase: GetUserSharedPreferenceUseCase,
    private val saveUserSharedPreferenceUseCase: SaveUserSharedPreferenceUseCase,
) : ViewModel() {

    val TAG = "LoginFragmentViewModel"

    private val _resultLive = MutableLiveData<String>()
    val resultLive: LiveData<String> = _resultLive

    private val _state = MutableLiveData<CoinListState>()
    val state: LiveData<CoinListState> = _state

    private val _mm = MutableLiveData<User>()
    val mm: LiveData<User> = _mm

    private val _newsLiveData = MutableLiveData<Resource<User>>()
    val newsLiveData: LiveData<Resource<User>> = _newsLiveData


    fun isNewUser(): Boolean {
        return getUserSharedPreferenceUseCase.execute().id == 85000
    }

    fun getuserOfEmailPasswordApiViewModel(emailQuery: String, passwQuery: String) =
        viewModelScope.launch {
            _newsLiveData.postValue(Resource.Loading())
            val response = gGetUserOfEmailPasswordApiUseCase.invoke(emailQuery = emailQuery, passwQuery = passwQuery)

//            if (response.isSuccessful) {
//                _mm.postValue(response.body()!!)
//
//                response.body().let { res ->
//                    _newsLiveData.postValue(Resource.Success(res))
//                }
//            } else {
//                _newsLiveData.postValue(Resource.Error(message = response.message()))
//            }
        }

}