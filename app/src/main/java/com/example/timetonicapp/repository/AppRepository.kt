package com.example.timetonicapp.repository

import android.content.Context
import com.example.timetonicapp.api.RetrofitInstance
import com.example.timetonicapp.model.AppKeyResponse
import com.example.timetonicapp.model.OauthKeyResponse
import com.example.timetonicapp.model.SessKeyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository(private val context: Context) {

    // createAppKey function
    fun createAppKey(
        version: String,
        req: String,
        appname: String,
        onSuccess: (String) -> Unit, onFailure: (Throwable) -> Unit) {

//        if (!isNetworkAvailable(context)) {
//            onFailure(Exception("No internet connection"))
//            return
//        }

        RetrofitInstance.api.createAppkey(version, req, appname).enqueue(object :
            Callback<AppKeyResponse> {
            override fun onResponse(call: Call<AppKeyResponse>, response: Response<AppKeyResponse>) {
                if (response.isSuccessful) {
                    val appKey = response.body()?.appkey
                    val errorMsg = response.body()?.errorMsg
                    if (appKey != null) {
                        onSuccess(appKey)
                    } else if (errorMsg != null) {
                        onFailure(Exception(errorMsg))
                    } else {
                        onFailure(Exception("Could not create AppKey"))
                    }
                } else {
                    onFailure(Exception("Failed to create AppKey"))
                }
            }

            override fun onFailure(call: Call<AppKeyResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }

    // createOauthKey function
    fun createOauthKey(
        version: String,
        req: String,
        login: String,
        pwd: String,
        appKey: String,
        onSuccess: (String, String) -> Unit, onFailure: (Throwable) -> Unit) {

//        if (!isNetworkAvailable(context)) {
//            onFailure(Exception("No internet connection"))
//            return
//        }

        RetrofitInstance.api.createOauthkey(version, req, login, pwd, appKey).enqueue(object : Callback<OauthKeyResponse> {
            override fun onResponse(call: Call<OauthKeyResponse>, response: Response<OauthKeyResponse>) {
                if (response.isSuccessful) {
                    val oauthKey = response.body()?.oauthkey
                    val ou = response.body()?.o_u
                    if (oauthKey != null && ou != null) {
                        onSuccess(oauthKey, ou)
                    } else {
                        onFailure(Exception("OauthKey or o_u is null"))
                    }
                } else {
                    onFailure(Exception("Failed to create OauthKey"))
                }
            }

            override fun onFailure(call: Call<OauthKeyResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }

    // createSessKey function
    fun createSessKey(
        version: String,
        req: String,
        ou: String,
        uc: String,
        oauthKey: String,
        onSuccess: (String) -> Unit, onFailure: (Throwable) -> Unit) {

//        if (!isNetworkAvailable(context)) {
//            onFailure(Exception("No internet connection"))
//            return
//        }

        RetrofitInstance.api.createSesskey(version, req, ou, uc, oauthKey).enqueue(object : Callback<SessKeyResponse> {
            override fun onResponse(call: Call<SessKeyResponse>, response: Response<SessKeyResponse>) {
                if (response.isSuccessful) {
                    val sessKey = response.body()?.sesskey
                    if (sessKey != null) {
                        onSuccess(sessKey)
                    } else {
                        onFailure(Exception("SessKey is null"))
                    }
                } else {
                    onFailure(Exception("Failed to create SessKey"))
                }
            }

            override fun onFailure(call: Call<SessKeyResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}