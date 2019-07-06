package com.example.android.browse.repo

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SupportSQLiteQueryBuilder
import com.example.android.browse.api.LCBOApiService
import com.example.android.core.data.LCBOItemDao
import com.example.android.browse.vo.LCBOItemQueryParameters
import com.example.android.core.AppExecutors
import com.example.android.core.vo.LCBOItem
import com.example.android.core.vo.NetworkBoundResource
import com.example.android.core.vo.Resource
import javax.inject.Inject

class LCBOItemRepository @Inject constructor(
    val appExecutors: AppExecutors,
    val lcboItemDao: LCBOItemDao,
    val lcboApiService: LCBOApiService
) {

    fun searchLcboItems(
        queryParameters: LCBOItemQueryParameters
    ): LiveData<Resource<List<LCBOItem>>> {
        val query = SupportSQLiteQueryBuilder.builder("lcbo_item")
            .create()
        return object: NetworkBoundResource<List<LCBOItem>, List<LCBOItem>>(appExecutors) {
            override fun loadFromDb(): LiveData<List<LCBOItem>> {
               return lcboItemDao.getLcboItems(query)
            }

            override fun shouldFetch(data: List<LCBOItem>?) = true

            override fun createCall() = lcboApiService.fetchLcboItems(query)

            override fun saveCallResult(items: List<LCBOItem>) = lcboItemDao.insertLcboItems(items)

        }.asLiveData()
    }

}