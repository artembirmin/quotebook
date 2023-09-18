/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.model.network.api

import com.incetro.quotebook.entity.number.NumberFactResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DemoApi {

    @GET("http://numbersapi.com/{number}")
    suspend fun getNumberFact(
        @Path("number") number: Int,
        @Header("Content-Type") contentType: String = "application/json",
    ): NumberFactResponse
}