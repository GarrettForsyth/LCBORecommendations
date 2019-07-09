package com.example.android.core.data

import androidx.test.filters.SmallTest
import com.example.android.core.data.LCBOItemContract
import com.example.android.core.data.SupportSQLiteQueryConverter
import com.example.android.core.vo.LCBOItemQueryParameters
import com.google.common.truth.Truth.assertThat
import org.junit.Test

@SmallTest
class SupportSQLiteQueryConverterTest {

    private val converter = SupportSQLiteQueryConverter()

    // TODO: figure out a way to verify the selection arguments in the query
    @Test
    fun `it converts a simple string query`() {
        val params = LCBOItemQueryParameters(filterString = "beer")
        val query = converter.convert(params)

        val expected = "SELECT  *  FROM ${LCBOItemContract.TABLE_NAME} WHERE (${LCBOItemContract.COLUMN_NAME} LIKE ?)"
        assertThat(query.sql).isEqualTo(expected)
        assertThat(query.argCount).isEqualTo(1)
    }
}