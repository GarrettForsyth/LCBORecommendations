package com.example.android.core.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.android.core.vo.LCBOItem


@Dao
abstract class LCBOItemDao {

    @Query("SELECT * FROM lcbo_items WHERE id = :id")
    abstract fun getLcboItemById(id: Int): LiveData<LCBOItem>

    @RawQuery(observedEntities = [ LCBOItem::class ])
    abstract fun getLcboItems(query: SupportSQLiteQuery): LiveData<List<LCBOItem>>

    @Insert
    abstract fun insertLcboItems(items: List<LCBOItem>)

    @Insert
    abstract fun insertLcboItem(item: LCBOItem)


}