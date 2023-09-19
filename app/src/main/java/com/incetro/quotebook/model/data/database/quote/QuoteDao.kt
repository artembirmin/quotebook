/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.data.database.quote

import androidx.room.Dao
import androidx.room.Query
import com.incetro.quotebook.model.data.database.BaseDao
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface QuoteDao : BaseDao<QuoteDto> {

}