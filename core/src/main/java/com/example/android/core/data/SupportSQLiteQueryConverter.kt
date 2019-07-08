package com.example.android.core.data

import androidx.sqlite.db.SupportSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQueryBuilder
import com.example.android.core.vo.LCBOItemQueryParameters

/**
 * Converts [LCBOItemQueryParameters] into a [SupportSQLiteQuery].
 */
class SupportSQLiteQueryConverter {

    fun convert(params: LCBOItemQueryParameters): SupportSQLiteQuery {
        val queryBuilder = SupportSQLiteQueryBuilder.builder(LCBOItemContract.TABLE_NAME)

        val selection = StringBuilder("")
        val selectionArgs = Array(1) { "" }
        selection.append("(name LIKE ?)")
        selectionArgs[0] = ("%${params.filterString}%")

        return queryBuilder.selection(selection.toString(), selectionArgs).create()
    }
}