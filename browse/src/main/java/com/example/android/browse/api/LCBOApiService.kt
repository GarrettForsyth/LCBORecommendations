package com.example.android.browse.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.android.browse.vo.LCBOItemQueryParameters
import com.example.android.core.api.ApiResponse
import com.example.android.core.vo.LCBOItem

class LCBOApiService {

    fun fetchLcboItems(
        queryParameters: SupportSQLiteQuery
    ): LiveData<ApiResponse<List<LCBOItem>>>  {
        return MutableLiveData<ApiResponse<List<LCBOItem>>>()
    }
}