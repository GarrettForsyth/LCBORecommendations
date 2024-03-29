package com.example.android.core.vo

import com.example.android.core.vo.Status.SUCCESS
import com.example.android.core.vo.Status.LOADING
import com.example.android.core.vo.Status.ERROR

/**
 * A generic class that holds a value with its loading status.
 */
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}
