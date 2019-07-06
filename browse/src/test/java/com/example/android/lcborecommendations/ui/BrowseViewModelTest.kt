package com.example.android.lcborecommendations.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.android.browse.repo.LCBOItemRepository
import com.example.android.browse.ui.viewmodels.BrowseViewModel
import com.example.android.lcborecommendations.test.ktx.getValueBlocking
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
class BrowseViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var browseViewModel: BrowseViewModel

    private val lcboItemRepository: LCBOItemRepository = mockk(relaxed = true)

    // Must initialize in @Before block for the InstantTaskExecutorRule to take effect.
    @Before
    fun init() {
        browseViewModel = BrowseViewModel(lcboItemRepository)
    }

    @Test
    fun search_shouldCallRepositoryWithQueryParameters() {
        browseViewModel.search()
        verify { lcboItemRepository.searchLcboItems(browseViewModel.queryParameters) }
    }


    @Test
    fun toggleSortOptionsBottomSheetVisibility_shouldUpdateBottomSheetVisibilityState() {
        val visibilityState = browseViewModel.sortOptionsBottomSheetVisible
        // GIVEN - the initial bottom sheet visibility state is FALSE
        assertThat(visibilityState.getValueBlocking()).isFalse()

        // WHEN - toggleSortOptionsBottomSheetVisibility() is called
        browseViewModel.toggleSortOptionsBottomSheetVisibility()
        // THEN - the bottom sheet visibility state is TRUE
        assertThat(visibilityState.getValueBlocking()).isTrue()

        // WHEN - toggleSortOptionsBottomSheetVisibility() is called second time
        browseViewModel.toggleSortOptionsBottomSheetVisibility()
        // THEN - the bottom sheet visibility state is FALSE
        assertThat(visibilityState.getValueBlocking()).isFalse()
    }


}