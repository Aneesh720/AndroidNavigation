package com.example.testing


import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.testing.View.Login
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class LoginUITest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<Login> =
        ActivityScenarioRule<Login>(
            Login::class.java
        )

    @Before
    fun intentsInit() {
        // initialize Espresso Intents capturing
        Intents.init()
    }

    @After
    fun intentsTeardown() {
        // release Espresso Intents capturing
        Intents.release()
    }


    @Test
    fun loginActivity() {
        //Arrange
        onView(withId(R.id.email))
            .perform(typeText("anchananeeh@gmail.com"))

        onView(withId(R.id.password))
            .perform(typeText("Ajsk*1234"), closeSoftKeyboard())

        //Act
        onView(withId(R.id.loginButton)).perform(click())
        //Assert
        intended(allOf(hasAction(Intent.ACTION_SEND)))
    }


}