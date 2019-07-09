package com.example.android.lcborecommendations.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.example.android.browse.api.LCBOApiService
import com.example.android.core.data.LCBOItemDao
import com.example.android.browse.repo.LCBOItemRepository
import com.example.android.core.AppExecutors
import com.example.android.core.api.ApiResponse
import com.example.android.core.api.ApiSuccessResponse
import com.example.android.core.api.QueryOptionsMapConverter
import com.example.android.core.data.SupportSQLiteQueryConverter
import com.example.android.core.vo.LCBOItem
import com.example.android.core.vo.LCBOItemQueryParameters
import com.example.android.core.vo.Resource
import com.example.android.lcborecommendations.InstantAppExecutors
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

@SmallTest
class LCBOItemRepositoryTest {

    @get:Rule var instantExecutorRule = InstantTaskExecutorRule()

    private val appExecutors: AppExecutors = InstantAppExecutors()
    private val lcboItemDao: LCBOItemDao = mockk(relaxed = true)
    private val lcboApiService: LCBOApiService = mockk(relaxed = true)
    private val supportSQLiteQueryConverter: SupportSQLiteQueryConverter = mockk(relaxed = true)
    private val optionsMapConverter: QueryOptionsMapConverter = mockk(relaxed = true)

    private val lcboItemRepository = LCBOItemRepository(
        appExecutors,
        lcboItemDao,
        lcboApiService,
        supportSQLiteQueryConverter,
        optionsMapConverter
    )

    private val queryParameters: LCBOItemQueryParameters = mockk()

    @Test
    fun `searchLcboItems` () {
        // GIVEN - the database returns fake data -- dbData
        val dbData = MutableLiveData<List<LCBOItem>>()
        every { lcboItemDao.getLcboItems(any()) } returns dbData

        // AND - the api service successfully returns fake data -- networkData
        val networkData = emptyList<LCBOItem>()
        val call = MutableLiveData<ApiResponse<List<LCBOItem>>>()
        every { lcboApiService.fetchLcboItems(any()) } returns call

        // AND - the results are being observed
        val observer: Observer<Resource<List<LCBOItem>>> = mockk(relaxed = true)

        // WHEN - searchLcboItems is called
        val results = lcboItemRepository.searchLcboItems(queryParameters)
        results.observeForever(observer)

        // THEN - the database should have been queried once
        verify(exactly = 1) { lcboItemDao.getLcboItems(any()) }

        // WHEN - the database returns with any result
        dbData.postValue(null)

        // THEN - the api service should have fetched once
        verify(exactly = 1) { lcboApiService.fetchLcboItems(any()) }

        // WHEN - the LCBO API responds with a result
        call.postValue(ApiSuccessResponse(networkData))

        // THEN - the networkData should be inserted into the database
        verify(exactly = 1) { lcboItemDao.insertLcboItems(networkData) }

        // THEN - the database be queried a second time with for the new results
        verify(exactly = 2) { lcboItemDao.getLcboItems(any()) }
    }

}

