package com.example.timetonicapp.api

import com.example.timetonicapp.model.AppKeyResponse
import com.example.timetonicapp.model.OauthKeyResponse
import com.example.timetonicapp.model.SessKeyResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {


    @FormUrlEncoded
    @POST("/live/api.php")
    fun createAppkey(
        @Field("version") version: String,
        @Field("req") req: String,
        @Field("appname") appname: String
    ): Call<AppKeyResponse>

    @FormUrlEncoded
    @POST("/live/api.php")
    fun createOauthkey(
        @Field("version") version: String,
        @Field("req") req: String,
        @Field("login") login: String,
        @Field("pwd") pwd: String,
        @Field("appkey") appkey: String,
    ): Call<OauthKeyResponse>

    @FormUrlEncoded
    @POST("/live/api.php")
    fun createSesskey(
        @Field("version") version: String,
        @Field("req") req: String,
        @Field("o_u") ou: String,
        @Field("u_c") uc: String,
        @Field("oauthkey") oauthkey: String,
    ): Call<SessKeyResponse>
}