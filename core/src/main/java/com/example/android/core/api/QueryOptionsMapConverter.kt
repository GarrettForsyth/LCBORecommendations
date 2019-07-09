package com.example.android.core.api

import com.example.android.core.vo.LCBOItemQueryParameters

/**
 * Converts [LCBOItemQueryParameters] into a map that can be used by Retrofit.
 */
class QueryOptionsMapConverter {
    fun convert(params: LCBOItemQueryParameters): Map<String, String> {
        val queryOptions = mutableMapOf<String, String>()
        if (params.filterString.isNotEmpty()) {
            queryOptions[QUERY] = params.filterString
        }
        return queryOptions
    }

    companion object Options {
        private const val QUERY = "q"
    }
}