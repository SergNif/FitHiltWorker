package com.sergnfitness.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        var BASE_URL = // "https://gorest.co.in/public-api/"
            "http://195.234.208.168:8085/"
        //"https://gorest.co.in/public-api/" //"https://velmm.com/apis/" //volley_array.json

        fun getRetroInstance(): Retrofit {

            val loggin = HttpLoggingInterceptor()
            loggin.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
            client.addInterceptor(loggin)


            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        //    }
        private val retrofit by lazy{
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api: ApiServer by lazy {
            retrofit.create(ApiServer::class.java)
        }
    }
}
