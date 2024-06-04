package com.example.timetonicapp.api

import com.example.timetonicapp.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object for creating the Retrofit instance.
 */
object RetrofitInstance {
    // Creates a Retrofit instance with the provided base URL and convert through the Gson converter factory.
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}