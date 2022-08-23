package com.sergnfitness.android.fit.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergnfitness.data.api.ApiServer
import com.sergnfitness.data.api.RetrofitInstanceModule
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import com.sergnfitness.domain.usecase.*
import com.sergnfitness.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val gGetUserOfEmailPasswordApiUseCase: GetUserOfEmailPasswordApiUseCase,
    private val getUserOfIdApiUseCase: GetUserOfIdApiUseCase,
    private val apiRepository: ApiRepository,
    private val getUserSharedPreferenceUseCase: GetUserSharedPreferenceUseCase,
    private val saveUserSharedPreferenceUseCase: SaveUserSharedPreferenceUseCase,
) : ViewModel() {

    val TAG = "LoginFragmentViewModel"

    private val _resultLive = MutableLiveData<String>()
    val resultLive: LiveData<String> = _resultLive

    private val _userLiveData = MutableLiveData<User?>()
    val userLiveData: LiveData<User?> = _userLiveData

    private val _userResourceLiveData = MutableLiveData<Resource<Any>>()
    val userResourceLiveData: LiveData<Resource<Any>> = _userResourceLiveData


    fun isNewUser(): Boolean {
        return getUserSharedPreferenceUseCase.execute().id == 85000
    }

    fun queryOfEmaiPassword(email: String, password: String) = viewModelScope.launch {
        Log.e(TAG, "inside query")

        safeCallGetUserOfEmailPasswordViewModel(email, password)
    }

    suspend fun safeCallGetUserOfEmailPasswordViewModel(email: String, password: String) {
        _userResourceLiveData.postValue(Resource.Loading())

//        val retroService = RetrofitInstanceModule.getRetroInstance().create(ApiServer::class.java)
        val call =  apiRepository.getUserOfEmailPasswordRepos(emailQuery = email, passwQuery = password)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(TAG, "Retrofit 1")
                _userResourceLiveData.postValue(Resource.Error(t.message.toString()))
                _userLiveData.postValue(null)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                _userResourceLiveData.postValue(Resource.Loading(response))
                Log.e(TAG, "Retrofit 2 ${response.body()?.id}")
                if (response.isSuccessful && response.body() != null) {

                    // получен ответ от сервера после записи данных
                    response.body().let { res ->
                        _userLiveData.postValue(res)
                        _userResourceLiveData.postValue(Resource.Success(res))
                    }
                } else {
                    Log.e(TAG, "${response.message()} Retrofit 3")
                    _userLiveData.postValue(null)
                    _userResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                }
            }
        })


    }

}