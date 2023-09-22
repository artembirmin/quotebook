/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.data.database.author

import androidx.room.Dao
import androidx.room.Query
import com.incetro.quotebook.model.data.database.BaseDao

@Dao
interface AuthorDao : BaseDao<AuthorDto> {
    @Query("SELECT * FROM ${AuthorDto.TABLE_NAME} WHERE name = :name")
    suspend fun getAuthorByName(name: String): AuthorDto?
}