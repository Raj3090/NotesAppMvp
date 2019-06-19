package com.example.notesappmvp.ui.notes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.notesappmvp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NotesScreenTest {


    @Rule
    @JvmField
    var tasksActivityTestRule = object :
        ActivityTestRule<NotesActivity>(NotesActivity::class.java) {
        /**
         * To avoid a long list of tasks and the need to scroll through the list to find a
         * task, we call [NotesDataSource.deleteAllTasks] before each test.
         */
        override
         fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            // Doing this in @Before generates a race condition.
        }
    }
    


    @Test
    public fun test_add_note_fab(){

        onView(withId(R.id.fab)).perform(click())

        // Check if the add task screen is displayed
        onView(withId(R.id.addTaskTitleTv)).check(matches(isDisplayed()))


    }

}