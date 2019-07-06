package com.example.android.browse.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.android.browse.vo.BrowseLCBOItemQueryParameters
import com.example.android.core.vo.LCBOItem


@Dao
abstract class LCBOItemDao {

    abstract fun getLcboItems(queryParameters: BrowseLCBOItemQueryParameters): LiveData<List<LCBOItem>>

    abstract fun insertLcboItems(items: List<LCBOItem>)

}