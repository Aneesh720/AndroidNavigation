package com.example.testing

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import net.bytebuddy.matcher.ElementMatchers.any
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        Timber.plant(Timber.DebugTree())
        scenario= launchActivity()
        scenario.moveToState(Lifecycle.State.RESUMED)

    }

    @After
    fun tearDown() {
    }

    @Test
    fun CheckReloadVisibility() {
        //Arrange
        onView(withId(R.id.reload)).perform(click())
        onView(withId(R.id.cart)).perform(click())
        //Act
        //Assert
        onView(withId(R.id.reload)).check(matches(not(isDisplayed())))

        //Arrange
        onView(withId(R.id.history)).perform(click())
        //Act
        //Assert
        onView(withId(R.id.reload)).check(matches(not(isDisplayed())))

        //Arrange
        var newImage:String= dogImage
        var historyImage:String= historyList[historyList.size-1]
        onView(withId(R.id.history)).perform(click())
        //Act
        //Assert
        Assert.assertEquals(true, newImage==historyImage)

        //Arrange

        onView(withId(R.id.dashboard)).perform(click())
        var price:String="1234.0"
        onView(withId(R.id.image)).perform(click())
        onView(withId(R.id.editText))
            .perform(ViewActions.typeText(price.toString()), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.cart)).perform(click())

        var addedDogImage:String= cartList[cartList.size-1].image

        //Act

        //Assert

        Assert.assertEquals(true, dogImage==addedDogImage)

        //Arrange

        newImage= dogImage
        historyImage= historyList[historyList.size-1]
        onView(withId(R.id.history)).perform(click())
        //Act
        //Assert
        onView(withId(R.id.dashboard)).perform(click())
        //Arrange
        var previousImage:String= dogImage
        onView(withId(R.id.reload)).perform(click())
        onView(withId(R.id.image)).check(matches(isDisplayed()))
        newImage= dogImage

        //Act
        //Assert

        Assert.assertEquals(previousImage, newImage)
    }
//    @Test
//    fun CheckReloadVisibilityHistory() {
//        //Arrange
//        onView(withId(R.id.reload)).perform(click())
//        onView(withId(R.id.history)).perform(click())
//        //Act
//        //Assert
//        onView(withId(R.id.reload)).check(matches(not(isDisplayed())))
//    }
//    @Test
//    fun CheckTheHistoryList() {
//        //Arrange
//        var newImage:String= dogImage
//        var historyImage:String= historyList[historyList.size-1]
//        onView(withId(R.id.history)).perform(click())
//        //Act
//        //Assert
//        Assert.assertEquals(true, newImage==historyImage)
//    }
//
//    @Test
//    fun CheckTheDialogOperation() {
//        //Arrange
//        var price="1234"
//        onView(withId(R.id.image)).perform(click())
//        onView(withId(R.id.editText))
//            .perform(ViewActions.typeText(price))
//        onView(withId(R.id.cart)).perform(click())
//
//        var addedPrice:String= cartList[cartList.size-1].price.toString()
//        var addedDogImage:String= cartList[cartList.size-1].image
//
//        //Act
//
//        //Assert
//        Assert.assertEquals(true,addedPrice==price)
//        Assert.assertEquals(true,addedPrice==addedDogImage)
//    }
//
//    @Test
//    fun CheckTheCartList() {
//        //Arrange
//        var newImage:String= dogImage
//        var historyImage:String= historyList[historyList.size-1]
//        onView(withId(R.id.history)).perform(click())
//        //Act
//        //Assert
//
//        onView(withId(R.id.image)).perform(click())
//        Assert.assertEquals(true, newImage==historyImage)
//    }
//
//    @Test
//    fun CheckTheReloadOperation() {
//        //Arrange
//        var previousImage:String= dogImage
//        onView(withId(R.id.reload)).perform(click())
//        var newImage:String= dogImage
//
//        //Act
//        //Assert
//
//        Assert.assertEquals(true, previousImage!=newImage)
//    }
}