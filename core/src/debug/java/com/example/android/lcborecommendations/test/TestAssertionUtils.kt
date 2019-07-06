package com.example.android.lcborecommendations.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.core.databinding.LcboItemListItemBinding
import com.example.android.core.ui.DataBoundViewHolder
import com.example.android.core.vo.LCBOItem
import com.example.android.lcborecommendations.test.CustomMatchers.hasItemAtPosition
import org.hamcrest.CoreMatchers.allOf

/**
 * Scrolls down a recycler view and verifies its contents match that of the passed
 * in list of LCBOItems (order sensitive).
 *
 * @param recyclerViewId the id of the recyclerview to chec
 * @param content the list of [LCBOItem] the recyclerview is expected to display.
 */
fun verifyRecyclerViewLCBOItemContents(recyclerViewId: Int, content: List<LCBOItem>) {
    content.forEachIndexed { position, expectedItem ->
        onView(withId(recyclerViewId))
            .perform(
                RecyclerViewActions
                    .scrollToPosition<DataBoundViewHolder<LcboItemListItemBinding>>(position)
            )
        onView(withId(recyclerViewId))
            .check(
                matches(
                    // Check all items of interest are visible here!
                    allOf(
                        hasItemAtPosition(hasDescendant(withText(expectedItem.name)), position)
                    )
                )
            )
    }

}