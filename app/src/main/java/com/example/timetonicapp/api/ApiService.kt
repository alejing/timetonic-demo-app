package com.example.timetonicapp.api

import com.example.timetonicapp.model.AppKeyResponse
import com.example.timetonicapp.model.BookResponse
import com.example.timetonicapp.model.OauthKeyResponse
import com.example.timetonicapp.model.SessKeyResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Interface for interacting with the backend API.
 */
interface ApiService {

    /**
     * Creates a new appKey on the API.
     * Params: version - The version of the API.
     *         req - The request type.
     *         appname - The name of the app.
     * @return The created appKey object.
     */
    @FormUrlEncoded
    @POST("/live/api.php")
    fun createAppkey(
        @Field("version") version: String,
        @Field("req") req: String,
        @Field("appname") appname: String
    ): Call<AppKeyResponse>

    /**
     * Creates a new oauthKey on the API.
     * Params: version - The version of the API.
     *         req - The request type.
     *         login - The login of the user.
     *         pwd - The password of the user.
     *         appkey - The appKey of the user.
     * @return The created oauthKey object.
     */
    @FormUrlEncoded
    @POST("/live/api.php")
    fun createOauthkey(
        @Field("version") version: String,
        @Field("req") req: String,
        @Field("login") login: String,
        @Field("pwd") pwd: String,
        @Field("appkey") appkey: String,
    ): Call<OauthKeyResponse>

    /**
     * Creates a new sessKey on the API.
     * Params: version - The version of the API.
     *         req - The request type.
     *         o_u - The oauth_user.
     *         u_c - The user.
     *         oauthkey - The oauthKey of the user.
     * @return The created sessKey object.
     */
    @FormUrlEncoded
    @POST("/live/api.php")
    fun createSesskey(
        @Field("version") version: String,
        @Field("req") req: String,
        @Field("o_u") ou: String,
        @Field("u_c") uc: String,
        @Field("oauthkey") oauthkey: String,
    ): Call<SessKeyResponse>

    /**
     * Retrieves a list of books from the API.
     * Params: version - The version of the API.
     *         req - The request type.
     *         o_u - The oauth_user.
     *         u_c - The user.
     *         sesskey - The sessKey of the user.
     * @return A list of books objects.
     */
    @FormUrlEncoded
    @POST("/live/api.php")
    fun getAllBooks(
        @Field("version") version: String,
        @Field("req") req: String,
        @Field("o_u") ou: String,
        @Field("u_c") uc: String,
        @Field("sesskey") sesskey: String,
    ): Call<BookResponse>
}