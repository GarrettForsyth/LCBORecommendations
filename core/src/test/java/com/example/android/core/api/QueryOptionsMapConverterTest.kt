package com.example.android.core.api

import androidx.test.filters.SmallTest
import com.example.android.core.vo.LCBOItemQueryParameters
import com.google.common.truth.Truth.assertThat
import org.junit.Test

@SmallTest
class QueryOptionsMapConverterTest {

    private val queryOptionsMapConverter = QueryOptionsMapConverter()

    @Test
    fun `it converts a filter string to query map`() {
        val expected = mutableMapOf<String, String>().apply { put("q", "beer")}
        val queryParameters = LCBOItemQueryParameters(filterString = "beer")
        val actual = queryOptionsMapConverter.convert(queryParameters)
        assertThat(actual).isEqualTo(expected)
    }

}