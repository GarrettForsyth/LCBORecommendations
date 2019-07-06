package com.example.android.lcborecommendations.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.browse.R
import com.example.android.browse.ui.fragments.BrowseFragment
import com.example.android.browse.vo.SortOrder
import com.example.android.core.databinding.LcboItemListItemBinding
import com.example.android.core.ui.DataBoundViewHolder
import com.example.android.core.ui.ViewModelUtil
import com.example.android.core.vo.LCBOItem
import com.example.android.lcborecommendations.test.listOfLcboItems
import com.example.android.lcborecommendations.test.verifyRecyclerViewLCBOItemContents
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.LooperMode
import org.robolectric.annotation.TextLayoutMode
import kotlin.random.Random

/**
 * Integration test for the browse screen.
 */
@MediumTest
@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
@TextLayoutMode(TextLayoutMode.Mode.REALISTIC)
class BrowseFragmentTest {

    private lateinit var scenario: FragmentScenario<TestBrowseFragment>

    @Before
    fun launchBrowseFragment() {
        //GIVEN - the BrowseFragment is launched
        scenario = launchFragmentInContainer<TestBrowseFragment>(
            null, R.style.AppTheme, TestBrowseFragmentFactory())
    }

    @Test
    fun browseScreen_displaysControls() {
        // THEN - initial views are displayed
        onView(withId(R.id.browse_query_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.browse_sort_order_button)).check(matches(isDisplayed()))
        onView(withId(R.id.browse_sort_direction_toggle)).check(matches(isDisplayed()))

        // THEN - initial views are not displayed
        onView(withId(R.id.browse_sort_order_options_bottom_sheet))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.browse_search_results_list)) // Initially results are empty
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun whenKeystrokeInQueryEditText_shouldUpdateViewModel() {
        // WHEN - The word 'dogs' is entered into the search query edit text
        val filterString = "dogs"
        onView(withId(R.id.browse_query_edit_text)).perform(typeText(filterString))
        // THEN - the viewmodel is updated for each keystroke made
        scenario.onFragment {
            verify {
                it.viewModel.queryParameters.filterString = "d"
                it.viewModel.queryParameters.filterString = "do"
                it.viewModel.queryParameters.filterString = "dog"
                it.viewModel.queryParameters.filterString = "dogs"
            }
        }
    }

    @Test
    fun whenSearchButtonIsPressed_shouldCallSearchOnViewModel() {
        // WHEN - the search button is pressed
        onView(withId(R.id.browse_search_button)).perform(click())
        // THEN - the viewmodel calls search
        scenario.onFragment {
            verify {
                it.viewModel.search()
            }
        }
    }

    @Test
    fun whenResultsAreAvailable_shouldBeDisplayedInResultsList() {
        // GIVEN - the viewmodel is updated with the search results
        val numberOfItems = Random.nextInt(1,20)
        val searchResults = listOfLcboItems(numberOfItems)
        scenario.onFragment {
            it.fakeSearchResults.value = searchResults
        }

        // THEN - the search result recyclerview should update with the search results
        verifyRecyclerViewLCBOItemContents(R.id.browse_search_results_list, searchResults)
    }

    @Test
    fun sortOptionsBottomSheetIsShown_whenShowSortOptionsBottomSheetIsTrue() {
        scenario.onFragment { it.fakeSortOptionsBottomSheetVisible.value = true }
        onView(withId(R.id.browse_sort_order_options_bottom_sheet))
            .check(matches(isDisplayed()))
    }

    @Test
    fun sortOptionsBottomSheetIsNotShown_whenShowSortOptionsBottomSheetIsFalse() {
        scenario.onFragment { it.fakeSortOptionsBottomSheetVisible.value = false }
        onView(withId(R.id.browse_sort_order_options_bottom_sheet))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun clickingSortOrderButton_requestViewModelToShowSortOrderOptionsBottomSheet() {
        onView(withId(R.id.browse_sort_order_button)).perform(click())
        scenario.onFragment {
            verify {
                it.viewModel.toggleSortOptionsBottomSheetVisibility()
            }
        }
    }

    @Test
    fun clickingOnSortOrderOption_shouldUpdateViewModel() {
        // GIVEN - the bottom sort order option sheet is visible
        scenario.onFragment { it.fakeSortOptionsBottomSheetVisible.value = true }

        // WHEN - the 'Alcohol Content' sort order is selected
        onView(withText("Alcohol Content")).perform(click())

        // THEN - the fragment should tell the view model to close the bottom sheet
        scenario.onFragment {
            verify {
                it.viewModel.toggleSortOptionsBottomSheetVisibility()
            }
        }
        // AND - the query parameters should be updated with the new sort order
        scenario.onFragment {
            verify {
                it.viewModel.queryParameters.sortOrder = SortOrder.ALCOHOL_CONTENT
            }
        }
        // AND - the browse_sort_order_button text has been changed to 'Alcohol Content'
        onView(withId(R.id.browse_sort_order_button))
            .check(matches(withText("Alcohol Content")))
    }

}

/**
 * TestBrowseFragment which replaces the dependencies of BrowseFragment with mocks/fakes.
 */
class TestBrowseFragment: BrowseFragment() {
    private val fakeNavController: NavController = mockk(relaxed = true)
    override fun navController() = fakeNavController
    val fakeSortOptionsBottomSheetVisible = MutableLiveData<Boolean>()
    val fakeSearchResults = MutableLiveData<List<LCBOItem>>()
}

class TestBrowseFragmentFactory: FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return (super.instantiate(classLoader, className) as TestBrowseFragment).apply {
            this.viewModel = mockk(relaxed = true)
            this.viewModelFactory = ViewModelUtil.createFor(this.viewModel)
            every { viewModel.sortOptionsBottomSheetVisible } returns fakeSortOptionsBottomSheetVisible
            every { viewModel.searchResults } returns fakeSearchResults
        }
    }
}
