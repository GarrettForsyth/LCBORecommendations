package com.example.android.lcborecommendations.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.android.browse.api.LCBOApiService
import com.example.android.browse.api.LiveDataCallAdapterFactory
import com.example.android.core.api.ApiSuccessResponse
import com.example.android.core.api.EnvelopeConverterFactory
import com.example.android.core.vo.LCBOItem
import com.example.android.lcborecommendations.test.ktx.getValueBlocking
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import io.mockk.mockk
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.Assert.assertEquals
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SmallTest
class LCBOApiServiceTest {

    @get:Rule val instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var lcboApiService: LCBOApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()

        lcboApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(EnvelopeConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(LCBOApiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch lcbo items`() {
        // GIVEN - a set of query parameters
        val queryMap: Map<String,String> = mockk(relaxed = true)

        // AND - the server's expected response to a query is
        enqueue("search_beer.json")

        // WHEN - the lcboApiService is queried with them
        val response = lcboApiService.fetchLcboItems(queryMap)

        // THEN - it returns a list of LCBO items matching the query
        val expectedItemsJson = LCBOApiServiceTest::class.java.getResource("/expected_beer_results.json").readText()
        val expectedResults = Gson().fromJson(expectedItemsJson, Array<LCBOItem>::class.java).toList()
        val results = (response.getValueBlocking() as ApiSuccessResponse<List<LCBOItem>>).body
        assertEquals(expectedResults, results)
    }

    @Test
    fun `service queries correct endpoint from query parameter`() {
        // GIVEN - a set of query parameters
        val queryMap = mutableMapOf<String, String>()
            .apply { putIfAbsent("q", "beer") }

        // WHEN - the lcboApiService is queried with them
        lcboApiService.fetchLcboItems(queryMap).getValueBlocking()

        // AND - the app queries the expected address
        val requestPath= mockWebServer.takeRequest().path
        val expectedRequestPath = "/products?q=beer"
        assertThat(requestPath).isEqualTo(expectedRequestPath)
    }

    @Test
    fun `service queries correct endpoints multword query parameter`() {
        // GIVEN - a set of query parameters
        val queryMap = mutableMapOf<String, String>()
            .apply { putIfAbsent("q", "red wine") }

        // WHEN - the lcboApiService is queried with them
        lcboApiService.fetchLcboItems(queryMap).getValueBlocking()

        // AND - the app queries the expected address
        val requestPath= mockWebServer.takeRequest().path
        val expectedRequestPath = "/products?q=red%20wine"
        assertThat(requestPath).isEqualTo(expectedRequestPath)
    }

    private fun enqueue(filename: String) {
        val json = LCBOApiServiceTest::class.java.getResource("/$filename").readText()
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))
    }
}
