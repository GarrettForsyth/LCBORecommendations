package com.example.android.browse.api

import androidx.lifecycle.LiveData
import com.example.android.core.api.ApiResponse
import com.example.android.core.vo.LCBOItem
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface LCBOApiService {

    @GET("/products")
    fun fetchLcboItems(
        @QueryMap options: Map<String,String>
    ): LiveData<ApiResponse<List<LCBOItem>>>

}