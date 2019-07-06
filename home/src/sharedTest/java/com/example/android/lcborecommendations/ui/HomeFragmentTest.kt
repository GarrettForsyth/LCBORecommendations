package com.example.android.lcborecommendations.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.core.Event
import com.example.android.core.ui.ViewModelUtil
import com.example.android.home.R
import com.example.android.home.ui.fragments.HomeFragment
import com.example.android.home.ui.fragments.HomeFragmentDirections
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.LooperMode
import org.robolectric.annotation.TextLayoutMode

/**
 * Integration test for the home screen.
 */
@MediumTest
@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
@TextLayoutMode(TextLayoutMode.Mode.REALISTIC)
class HomeFragmentTest {

    private lateinit var scenario: FragmentScenario<TestHomeFragment>

    @Before
    fun launchHomeFragment() {
        //GIVEN - the HomeFragment is launched
        scenario = launchFragmentInContainer<TestHomeFragment>(
            null, R.style.AppTheme, TestHomeFragmentFactory())
    }

    @Test
    fun homeScreen_displaysButtons() {
        // THEN - the navigate to browse button is displayed
        onView(withId(R.id.browse_button)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldNavigateToBrowseFragment_whenBrowseCommandOccurs() {
        // WHEN - a browse command is received
        scenario.onFragment { it.fakeBrowseCommand.postValue(Event(Unit)) }

        // THEN - the navigation controller navigates to the BrowseFragment
        scenario.onFragment {
            verify {
                it.navController().navigate(HomeFragmentDirections.actionHomeToBrowse())
            }
        }

    }
}

/**
 * TestFragment which replaces the dependencies of HomeFragment with mocks/fakes.
 */
class TestHomeFragment: HomeFragment() {
    val fakeBrowseCommand = MutableLiveData<Event<Unit>>()
    private val fakeNavController: NavController = mockk(relaxed = true)
    override fun navController() = fakeNavController
}

class TestHomeFragmentFactory: FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return (super.instantiate(classLoader, className) as TestHomeFragment).apply {
            this.viewModel = mockk(relaxed = true)
            this.viewModelFactory = ViewModelUtil.createFor(this.viewModel)
            every { viewModel.browseCommand } returns fakeBrowseCommand
        }
    }
}