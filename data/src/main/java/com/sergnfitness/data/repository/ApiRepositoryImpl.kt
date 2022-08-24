package com.sergnfitness.data.repository

import android.app.Application
import android.util.Log
import com.sergnfitness.data.R
import com.sergnfitness.data.api.ApiServer
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val api: ApiServer,
    appContext: Application
    ) : ApiRepository {

    init{
        val appName = appContext.getString(androidx.core.R.string.status_bar_notification_info_overflow)
        Log.e("ApiRepositoryImpl", "Heloo from ApiRepositoryImpl $appName")
    }

    override suspend fun getUserOfIdRepos(id: Int): Call<User> {
        return api.getUserOfId(id = id)//, emailQuery = email, passwQuery = password) //.body().toUser()
    }

    override suspend fun getUserOfEmailPasswordRepos(emailQuery:String, passwQuery:String) : Call<User> {
        return api.getUserOfEmailPassword(emailQuery = emailQuery, passwQuery = passwQuery)//, emailQuery = email, passwQuery = password) //.body().toUser()
    }


//    suspend fun getMenu() = api.getHeadLines(5, "28-05-2022" )
}