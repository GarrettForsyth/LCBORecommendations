package com.example.android.browse.api

import androidx.lifecycle.LiveData
import com.example.android.core.api.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Retrofit adapter that converts the Call into a LiveData ApiResponse
 * @param <R> the type the ApiResponse holds (ApiResponse<R>)
 */
class LiveDataCallAdapter<R>(private val responseType: Type) : CallAdapter<R, LiveData<ApiResponse<R>>> {

    override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
        return object : LiveData<ApiResponse<R>>() {

            private var started = AtomicBoolean(false)

            override fun onActive() {
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onFailure(call: Call<R>, t: Throwable) {
                            postValue(ApiResponse.create(t))
                        }

                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(ApiResponse.create(response))
                        }
                    })
                }
            }
        }
    }

    override fun responseType() = responseType
}