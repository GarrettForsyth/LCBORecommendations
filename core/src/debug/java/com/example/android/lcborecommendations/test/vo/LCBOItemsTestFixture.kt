package com.example.android.lcborecommendations.test.vo

import com.example.android.core.api.LCBOApiResponse
import com.example.android.core.api.Pager
import com.example.android.core.vo.LCBOItem
import com.google.gson.Gson

/**
 * Represents a fixed starting state for the database or a
 * fixed data response from the LCBO API.
 */
data class LCBOItemsTestFixture(
    val items: List<LCBOItem>
) {

    private val gson = Gson()

    fun asLcboApiResponseJSON(): String {
        val pageSize = 20
        val pager: Pager = Pager(
            recordsPerPage = pageSize,
            totalRecordCount = items.size,
            currentPageRecordCount =  if (items.size < pageSize) items.size else pageSize,
            isFirstPage = true,
            isFinalPage = false,
            currentPage = 1,
            currentPagePath = "somePagePath",
            nextPage = 2,
            nextPagePath = "nextPagePath",
            previousPage = null,
            previousPagePath = "previousPagePath",
            totalPages = 1,
            totalPagesPath = "totalPagesPath"
        )
        val response: LCBOApiResponse<List<LCBOItem>> =
            LCBOApiResponse(
                status = 200,
                message = null,
                pager = pager,
                result = items
            )
        return gson.toJson(response)
    }
}
