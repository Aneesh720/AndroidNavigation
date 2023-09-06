package com.example.testing

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SplashScreenTest{
    private lateinit var scenario: ActivityScenario<SplashScreen>

    @Before
    fun setUp() {
        scenario= launchActivity()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun CheckReloadVisibility() {
        //Arrange

        Espresso.onView(ViewMatchers.withId(R.id.reload)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.cart)).perform(ViewActions.click())
        //Act
        //Assert
        Espresso.onView(ViewMatchers.withId(R.id.reload))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }
}