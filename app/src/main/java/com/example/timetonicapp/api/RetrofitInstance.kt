package com.example.timetonicapp.api

import com.example.timetonicapp.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    //private const val BASE_URL = "https://fakerapi.it/api/v1/"
    //private const val BASE_URL = "https://timetonic.com/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}