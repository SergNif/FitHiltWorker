package com.sergnfitness.data.api


import com.sergnfitness.data.storage.storageModel.DataUserStorage
import com.sergnfitness.data.storage.storageModel.UserStorage
import com.sergnfitness.domain.models.user.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiServer {
//    @GET("/get_one1_user/{user_id}")
//    suspend fun getUserOfId(
//        @Path("user_id") id: Int
////        @Query("emailQuery")
////        emailQuery: String,
////        @Query("passwQuery")
////        passwQuery: String,
//    ): Response<User>

    @POST("new_user/")
    fun saveNewUserOfEmailPassword(
        @Body params: User
        ): Call<User>


    @GET("/get_one_user/{user_id}")
    fun getUserOfId(
        @Path("user_id") id: Int
//        @Query("emailQuery")
//        emailQuery: String,
//        @Query("passwQuery")
//        passwQuery: String,
    ): Call<User>

//    @GET("/fit_get_one_user_email/")
//    suspend fun getUserOfEmailPassword(
////        @Path("user_id") id: Int
//        @Query("emailQuery")
//        emailQuery: String,
//        @Query("passwQuery")
//        passwQuery: String,
//    ): Response<User>
//
    @GET("/fit_get_one_user_email/")
    fun getUserOfEmailPassword(
//        @Path("user_id") id: Int
        @Query("emailQuery")
        emailQuery: String,
        @Query("passwQuery")
        passwQuery: String,
    ): Call<User>

//    @GET("/fit_get_menu_string/")
//    suspend fun getHeadLines(
//        @Query("userMenuQiery") id: Int,
//        @Query("dataMenu") date: String
//    ): Response<MenuDayListStorage>

}