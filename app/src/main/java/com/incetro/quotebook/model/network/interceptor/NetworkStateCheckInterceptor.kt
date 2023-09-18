/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.model.network.interceptor

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.incetro.quotebook.model.network.NetworkConnectionError
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkStateCheckInterceptor @Inject constructor(
    private val connectivityManager: ConnectivityManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkConnected()) {
            throw NetworkConnectionError()
        }

        return chain.proceed(chain.request())
    }

    private fun isNetworkConnected(): Boolean {
        return postAndroidMInternetCheck()
    }

    private fun postAndroidMInternetCheck(): Boolean {
        val network = connectivityManager.activeNetwork
        val connection =
            connectivityManager.getNetworkCapabilities(network)

        return connection != null && (
                connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }
}