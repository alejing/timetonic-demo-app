package com.example.timetonicapp.ui.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.timetonicapp.repository.AppRepository
import com.example.timetonicapp.utils.AppConstants

/**
 * ViewModel for the Login screen.
 */
class LoginViewModel (application: Application): AndroidViewModel(application){

    // // Instantiate the repository
    private val repository = AppRepository(application.applicationContext)

    // Authentication callback
    interface AuthCallback {
        fun onAuthSuccess(message: String, ou: String, sesskey: String)
        fun onAuthError(error: String)
    }

    // Callback variable
    private var authCallback: AuthCallback? = null

    // Set callback variable
    fun setAuthCallback(callback: AuthCallback?) {
        this.authCallback = callback
    }

    // Init authentication flow in the view model
    fun startAuthentication(login: String, password: String) {
        createAppKey(login, password)
    }

    // Create appKey with login and password parameters
    private fun createAppKey(login: String, password: String) {
        // Call createAppKey
        repository.createAppKey(AppConstants.VERSION, AppConstants.REQ_APPKEY, AppConstants.APP_NAME, { appKey ->
            // Flow continue to createOauthKey
            createOauthKey(login, password, appKey)
        }, { error ->
            authCallback?.onAuthError("Authenticated appKey failed: ${error.message}")
        })
    }

    // Create oauthKey with login, password and appKey parameters
    private fun createOauthKey(login: String, password: String, appKey: String) {
        // Call createOauthKey
        repository.createOauthKey(AppConstants.VERSION, AppConstants.REQ_OAUTHKEY, login, password, appKey, { oauthKey, ou ->
            // Flow continue to createSessKey
            createSessKey(ou, oauthKey)
        }, { error ->
            authCallback?.onAuthError("Authenticated oauthKey failed: ${error.message}")
        })
    }

    // Create session key with oauthKey and ou parameter
    private fun createSessKey(ou: String, oauthKey: String) {
        repository.createSessKey(AppConstants.VERSION, AppConstants.REQ_SESSKEY, ou, ou, oauthKey, { sessKey ->
            // sessKey created successfully
            authCallback?.onAuthSuccess("Authenticated with sessKey: $sessKey", ou, sessKey)
        }, { error ->
            // sessKey not created
            authCallback?.onAuthError("Authenticated sessKey failed: ${error.message}")
        })
    }

    // Clean up the callback
    override fun onCleared() {
        super.onCleared()
        authCallback = null
    }
}