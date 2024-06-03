package com.example.timetonicapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Checks if the device is currently connected to a network.
 *
 * @return True if the device is connected to a network, false otherwise.
 */
fun isNetworkAvailable(context: Context): Boolean {

    // Get the connectivity manager.
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // Check if the device is connected to a network.
    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    // Check if the network has a Wi-Fi or mobile data connection.
    return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
        NetworkCapabilities.TRANSPORT_CELLULAR)
}