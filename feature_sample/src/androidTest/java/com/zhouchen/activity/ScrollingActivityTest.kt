package com.zhouchen.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zhouchen.application.sample.R
import com.zhouchen.application.sample.activity.SampleListAdapter
import com.zhouchen.application.sample.activity.ScrollingActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ScrollingActivityTest {
    @get:Rule
    val rule = ActivityScenarioRule(ScrollingActivity::class.java)

    private val item_position = 4

    @Test
    fun launchScrollingActivity() {
       onView(withId(R.id.sample_list))
           .perform(RecyclerViewActions.scrollToHolder(isLast()))
           .check(matches(isDisplayed()))

        onView(withId(R.id.sample_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<SampleListAdapter.ViewHolder>(item_position, click()))
    }

    private fun isLast(): Matcher<SampleListAdapter.ViewHolder> {
        return object : TypeSafeMatcher<SampleListAdapter.ViewHolder>() {
            override fun describeTo(description: Description?) {
                description?.appendText("last item")
            }

            override fun matchesSafely(viewHolder: SampleListAdapter.ViewHolder): Boolean {
                return viewHolder.getLast()
            }
        }
    }

}