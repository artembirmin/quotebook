/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.database.author

import androidx.room.Dao
import androidx.room.Query
import com.incetro.quotebook.model.database.BaseDao
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface AuthorDao : BaseDao<AuthorDto> {

}