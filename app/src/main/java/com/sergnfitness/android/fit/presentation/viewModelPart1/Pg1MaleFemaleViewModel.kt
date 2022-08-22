package com.sergnfitness.android.fit.presentation.viewModelPart1


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergnfitness.android.model.CoinListState
import com.sergnfitness.data.api.ApiServer
import com.sergnfitness.data.api.RetrofitInstance
import com.sergnfitness.domain.models.user.User

import com.sergnfitness.domain.usecase.GetUserOfIdApiUseCase
import com.sergnfitness.domain.usecase.GetUserSharedPreferenceUseCase
import com.sergnfitness.domain.usecase.SaveUserSharedPreferenceUseCase
import com.sergnfitness.domain.util.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class Pg1MaleFemaleViewModel @Inject constructor(
    private val getUserOfIdApiUseCase: GetUserOfIdApiUseCase,
//    private val NOTuSEsGetUserOfIdApiUseCase: NOTuSEsGetUserOfIdApiUseCase,
    private val getUserSharedPreferenceUseCase: GetUserSharedPreferenceUseCase,
    private val saveUserSharedPreferenceUseCase: SaveUserSharedPreferenceUseCase,
) : ViewModel() {

    //   private val userRepository: UserRepositoryImpl by lazy(LazyThreadSafetyMode.NONE) { UserRepositoryImpl(userStorage = SharedPrefUserStorage(requireContext().applicationContext)) }
//    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) { GetUserNameUseCase(userRepository = userRepository) }
//    private val getIdSharedPreference = GetIdSharedPreference()
//    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) { SaveUserNameUseCase(userRepository = userRepository) }
    val TAG = "Pg1MaleFemaleViewModel"
    private val _resultLive = MutableLiveData<String>()
    val resultLive: LiveData<String> = _resultLive

    private val _state = MutableLiveData<CoinListState>()
    val state: LiveData<CoinListState> = _state

    private val  _mm = MutableLiveData<User>()
    val mm:LiveData<User> = _mm

    private val _userLiveData = MutableLiveData<User?>()
    val userLiveData: LiveData<User?> = _userLiveData

    private val _userResourceLiveData = MutableLiveData<Resource<Any>>()
    val userResourceLiveData: LiveData<Resource<Any>> = _userResourceLiveData

    init {
        Log.e(TAG, "init Pg1MaleFemaleViewModel")
//        getCoins()
        Log.e(TAG, "init Pg1MaleFemaleViewModel")
    }

    fun save(textv: String) {

//        val params = SaveUserNameParam(email = textv.toString(),
//            idUser = 5)//User(5, "fulname", textv.toString(), "123", 5))
        val params = User(id = 5,
            fullName = textv.toString(),
            email = "em@il",
            password = "123",
            fitness_id = 25)
        val result: Boolean = saveUserSharedPreferenceUseCase.execute(params)
//        val result: Boolean = saveUserNameUseCase.execute(param = params)
        _resultLive.value = "Save $textv = $result"

    }

    fun load() {
        val userName: User = getUserSharedPreferenceUseCase.execute()

        val result: String =
            "Save ${userName.email} = ${userName.password} = ${userName.id} = ${userName.fullName}"
        _resultLive.value = result
    }

    fun queryOfEmaiPassword(email:String, password:String) = viewModelScope.launch {
        Log.e(TAG, "inside query")

        safeCallGetUserOfEmailPasswordViewModel(email, password)
    }
    suspend fun safeCallGetUserOfEmailPasswordViewModel(email:String, password:String) {
//        _newsLiveData.postValue(Resource.Loading())

        val retroService = RetrofitInstance.getRetroInstance().create(ApiServer::class.java)
        val call = retroService.getUserOfEmailPassword(emailQuery = email, passwQuery = password)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(TAG, "Retrofit 1")
                _userLiveData.postValue(null)
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    // получен ответ от сервера после записи данных
                    Log.e(TAG, "Retrofit 2 ${response.body()?.id}")
                    response.body().let { res ->
                        _userLiveData.postValue(res)
                    }
                } else {
                    _userLiveData.postValue(null)
                    Log.e(TAG, "Retrofit 3")
                }
            }
        })
    }
    fun queryOfId(id:Int) = viewModelScope.launch {
        Log.e(TAG, "inside query")

        safeCallGetUserOfId(id)
    }
    suspend fun safeCallGetUserOfId(id:Int) {
        _userResourceLiveData.postValue(Resource.Loading())
        Log.e(TAG, "Retrofit 0")
        val retroService = RetrofitInstance.getRetroInstance().create(ApiServer::class.java)
        val call = retroService.getUserOfId(id = id)
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

//    fun getNews(id: Int) =
//        viewModelScope.launch {
//            _newsLiveData.postValue(Resource.Loading())
//            val response = getUserOfIdApiUseCase.invoke(id = id)//repository.getNews(countryCode = countryCode, pageNumber = newsPage)
//            if (response.isSuccessful) {
//                response.body().let { res ->
//                    _newsLiveData.postValue(Resource.Success(res))
//                }
//            } else {
//                _newsLiveData.postValue(Resource.Error(message = response.message()))
//            }
//        }


//    fun getCoins() {
//        Log.e(TAG, "init Pg1MaleFemaleViewModel Get coins")
//        getUserOfIdApiUseCase().let { result ->
//            when (result) {
//                is Resource.Succes<*> -> {
//                    _state.value = CoinListState(coins = (result.data
//                        ?: "") as String) // CoinListState(coins = result.data ?: emptyList())
//
//                }
//                is Resource.Error<*> -> {
//                    _state.value =
//                        CoinListState(error = result.message ?: "An unexpected error occurred!")
//                }
//                is Resource.Loading<*> -> {
//                    _state.value = CoinListState(isLoading = true)
//                }
//            }
//        }//            launchIn(viewModelScope)
//    }

//    fun getuserOfIdApiViewModel(id: Int) =
//        viewModelScope.launch {
//            _newsLiveData.postValue(Resource.Loading())
//            val response = getUserOfIdApiUseCase.invoke(id = id)
//
//            if (response.isSuccessful) {
//                _mm.postValue(response.body()!!)
////                response.body().let { ress ->_mm }
//                response.body().let { res ->
//                    _newsLiveData  //.postValue(Resource.Succes(res))
//                    Log.e(TAG, "getuserOfIdApiViewModel  ${_newsLiveData.value}")
//                }
//            } else {
//                _newsLiveData.postValue(Resource.Error(message = response.message()))
//            }
//        }

}