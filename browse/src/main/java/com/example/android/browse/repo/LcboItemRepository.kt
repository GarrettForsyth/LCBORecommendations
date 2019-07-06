package com.example.android.browse.repo

import androidx.lifecycle.LiveData
import com.example.android.browse.api.LCBOApiService
import com.example.android.browse.data.LCBOItemDao
import com.example.android.browse.vo.BrowseLCBOItemQueryParameters
import com.example.android.core.AppExecutors
import com.example.android.core.api.ApiResponse
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
        queryParameters: BrowseLCBOItemQueryParameters
    ): LiveData<Resource<List<LCBOItem>>> {
        return object: NetworkBoundResource<List<LCBOItem>, List<LCBOItem>>(appExecutors) {
            override fun loadFromDb() = lcboItemDao.getLcboItems(queryParameters)

            override fun shouldFetch(data: List<LCBOItem>?) = true

            override fun createCall() = lcboApiService.fetchLcboItems(queryParameters)

            override fun saveCallResult(items: List<LCBOItem>) = lcboItemDao.insertLcboItems(items)

        }.asLiveData()
    }

}