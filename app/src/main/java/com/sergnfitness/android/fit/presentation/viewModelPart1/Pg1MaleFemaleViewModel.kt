package com.sergnfitness.android.fit.presentation.viewModelPart1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergnfitness.android.model.CoinListState
import com.sergnfitness.data.storage.storageModel.UserStorage
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.usecase.GetCoinsUseCase
import com.sergnfitness.domain.usecase.GetUserAriServer
import com.sergnfitness.domain.usecase.GetUserSharedPreferenceUseCase
import com.sergnfitness.domain.usecase.SaveUserSharedPreferenceUseCase
import com.sergnfitness.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Pg1MaleFemaleViewModel @Inject constructor(
    private val getUserAriServer: GetUserAriServer,
    private val getCoinsUseCase: GetCoinsUseCase,
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

    private val _newsLiveData = MutableLiveData<Resource<User>>()
    val newsLiveData: LiveData<Resource<User>> = _newsLiveData

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


    fun getCoins() {
        Log.e(TAG, "init Pg1MaleFemaleViewModel Get coins")
        getCoinsUseCase().let { result ->
            when (result) {
                is Resource.Succes<*> -> {
                    _state.value = CoinListState(coins = (result.data
                        ?: "") as String) // CoinListState(coins = result.data ?: emptyList())

                }
                is Resource.Error<*> -> {
                    _state.value =
                        CoinListState(error = result.message ?: "An unexpected error occurred!")
                }
                is Resource.Loading<*> -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }//            launchIn(viewModelScope)
    }

    fun getNews(id: Int) =
        viewModelScope.launch {
            _newsLiveData.postValue(Resource.Loading())
            val response = getUserAriServer.invoke(id = id)

            if (response.isSuccessful) {
                _mm.postValue(response.body()!!)
//                response.body().let { ress ->_mm }
                response.body().let { res ->
                    _newsLiveData.postValue(Resource.Succes(res))
                }
            } else {
                _newsLiveData.postValue(Resource.Error(message = response.message()))
            }
        }

}