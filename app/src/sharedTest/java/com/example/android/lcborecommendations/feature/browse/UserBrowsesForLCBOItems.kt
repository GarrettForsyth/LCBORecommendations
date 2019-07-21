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
import com.example.android.core.vo.LCBOItem
import com.example.android.lcborecommendations.MainActivity
import com.example.android.lcborecommendations.R
import com.example.android.lcborecommendations.test.CustomMatchers.hasItemAtPosition
import com.example.android.lcborecommendations.test.fixtures.fixtureForBeerQuerySortedAndFiltered
import com.example.android.lcborecommendations.test.verifyRecyclerViewLCBOItemContents
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * FEATURE TEST
 *
 * As person who enjoys to drink
 * I want to search the LCBO's inventory
 * So I can find new items that interest me
 */
@MediumTest
@RunWith(AndroidJUnit4::class)
class UserBrowsesForLCBOItems {

    // GIVEN - the user has just opened the app
    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    // AND - the API will return this list of LCBO items

    @Test
    fun userBrowsesForBeerStory() {
        // WHEN - I click the browse button
        onView(withId(R.id.browse_button))
            .perform(click())

        // THEN - I should be on the browse screen
        onView(withId(R.id.browse_container))
            .check(matches(isDisplayed()))

        // WHEN - I type 'beer' into the query edit text
        onView(withId(R.id.browse_query_edit_text))
            .perform(typeText("beer"))

        // AND - I click the search button
        onView(withId(R.id.browse_search_button))
            .perform(click())

        // THEN - I should see only the beers from the starting list
        val expectedResults: List<LCBOItem> = fixtureForBeerQuerySortedAndFiltered
        verifyRecyclerViewLCBOItemContents(R.id.browse_search_results_list, expectedResults)
    }

}
