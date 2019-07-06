package com.example.android.lcborecommendations.feature.browse

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.core.databinding.LcboItemListItemBinding
import com.example.android.core.ui.DataBoundViewHolder
import com.example.android.lcborecommendations.MainActivity
import com.example.android.lcborecommendations.R
import com.example.android.lcborecommendations.test.CustomMatchers.hasItemAtPosition
import com.example.android.lcborecommendations.test.createRandomBeerAndAlcoholContentList
import com.example.android.lcborecommendations.test.orderByAlcoholContentAndFilterPrimaryCategoryByBeer
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * FEATURE TEST
 *
 * As person who enjoys strong beers
 * I want to search for strong beers
 * So I can find new strong beers I haven't tried before
 */
@MediumTest
@RunWith(AndroidJUnit4::class)
class UserBrowsesForStrongBeerTest {

    // GIVEN - starting screen is the HomeFragment
    @get:Rule val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun userBrowsesForStrongBeerTest() {
        val queryItem = "beer"
        val sortOrder = "Alcohol Content"
        val expectedResults =
            orderByAlcoholContentAndFilterPrimaryCategoryByBeer(
                createRandomBeerAndAlcoholContentList())

        // WHEN - I click the browse button
        onView(withId(R.id.browse_button))
            .perform(click())

        // THEN - I should be on the browse screen
        onView(withId(R.id.browse_container))
            .check(matches(isDisplayed()))

        // WHEN - I type 'beer' into the query edit text
        onView(withId(R.id.browse_query_edit_text))
            .perform(typeText(queryItem))
        // AND - I choose to sort by 'alcohol content' descending
        onView(withId(R.id.browse_sort_order_button))
            .perform(click())
        onView(withText(sortOrder))
            .perform(click())
        onView(withId(R.id.browse_sort_direction_toggle))
            .perform(click())
        // AND - I click the search button
        onView(withId(R.id.browse_search_button))
            .perform(click())

        // THEN - I should see a list of beer sorted by its alcohol content
        // TODO: create a matcher that matches a recycler view with a list of expected lcbo items
        expectedResults.forEachIndexed { position, expectedItem ->
            onView(withId(R.id.browse_results_list))
                .perform(RecyclerViewActions
                    .scrollToPosition<DataBoundViewHolder<LcboItemListItemBinding>>(position)
                )
            onView(withId(R.id.browse_results_list))
                .check(matches(allOf(
                    hasItemAtPosition(hasDescendant(withText(expectedItem.name)), position)
                )))
        }
    }
}