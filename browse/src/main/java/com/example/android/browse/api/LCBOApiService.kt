package com.example.android.browse.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.browse.vo.BrowseLCBOItemQueryParameters
import com.example.android.core.api.ApiResponse
import com.example.android.core.vo.LCBOItem

class LCBOApiService {

    fun fetchLcboItems(
        queryParameters: BrowseLCBOItemQueryParameters
    ): LiveData<ApiResponse<List<LCBOItem>>>  {
        return MutableLiveData<ApiResponse<List<LCBOItem>>>()
    }
}