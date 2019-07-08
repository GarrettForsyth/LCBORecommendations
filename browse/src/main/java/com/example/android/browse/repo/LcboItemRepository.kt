package com.example.android.browse.repo

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SupportSQLiteQueryBuilder
import com.example.android.browse.api.LCBOApiService
import com.example.android.core.data.LCBOItemDao
import com.example.android.core.AppExecutors
import com.example.android.core.api.ApiResponse
import com.example.android.core.data.SupportSQLiteQueryConverter
import com.example.android.core.vo.LCBOItem
import com.example.android.core.vo.LCBOItemQueryParameters
import com.example.android.core.vo.NetworkBoundResource
import com.example.android.core.vo.Resource
import javax.inject.Inject

class LCBOItemRepository @Inject constructor(
    val appExecutors: AppExecutors,
    val lcboItemDao: LCBOItemDao,
    val lcboApiService: LCBOApiService,
    val queryConverter: SupportSQLiteQueryConverter
) {

    fun searchLcboItems(
        queryParameters: LCBOItemQueryParameters
    ): LiveData<Resource<List<LCBOItem>>> {
        return object: NetworkBoundResource<List<LCBOItem>, List<LCBOItem>>(appExecutors) {
            override fun loadFromDb(): LiveData<List<LCBOItem>> {
                println("load db")
               return lcboItemDao.getLcboItems(queryConverter.convert(queryParameters))
            }

            override fun shouldFetch(data: List<LCBOItem>?) = true

            override fun createCall(): LiveData<ApiResponse<List<LCBOItem>>> {
                println("create call")
                val queryMap = mutableMapOf<String,String>()
                return lcboApiService.fetchLcboItems(queryMap)
            }

            override fun saveCallResult(items: List<LCBOItem>) = lcboItemDao.insertLcboItems(items)

        }.asLiveData()
    }

}