package com.example.android.home.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.android.home.ui.viewmodels.HomeViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

@SmallTest
class HomeViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule var instantExecutorRule = InstantTaskExecutorRule()

    private val homeViewModel = HomeViewModel()

    @Test
    fun onBrowseButtonClick_shouldNavigateToBrowseFragment() {
        // WHEN - the browse button is clicked
        homeViewModel.browse()

        // THEN - a browseCommand event is dispatched
        val value = homeViewModel.browseCommand.value
        assertThat(value?.getContentIfNotHandled()).isNotNull()
    }
}