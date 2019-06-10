package com.example.android.lcborecommendations

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class HomeScreenNavigationTest {
    @Test
    fun `when browse button is pressed it should navigate to browse fragment`() {
        onView(withId(R.id.browse_button)).perform(click())
        onView(withId(R.id.browse_container)).check(matches(isDisplayed()))
    }
}