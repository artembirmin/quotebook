/*
 * Quotebook
 *
 * Created by artembirmin on 10/10/2023.
 */

package com.incetro.quotebook.model.data.network.interceptor

import com.incetro.quotebook.model.data.network.api.GptApiKeys
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GptAuthorizationInterceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${GptApiKeys.keys[0]}")
        val request = builder.build()
        return chain.proceed(request)
    }
}