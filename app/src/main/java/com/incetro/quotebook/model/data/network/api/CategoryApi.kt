/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.data.network.api

import com.incetro.quotebook.entity.category.CategoriesGptRequest
import com.incetro.quotebook.entity.category.CategoriesGptResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CategoryApi {
    @POST("completions")
    suspend fun getCategories(@Body categoriesRequest: CategoriesGptRequest): CategoriesGptResponse
}