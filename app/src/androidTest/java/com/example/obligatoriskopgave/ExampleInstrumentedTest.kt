package com.example.obligatoriskopgave

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun login() {
        ActivityScenario.launch(LoginRegisterActivity::class.java)

        val email = "anbo@zealand.dk"
        val password = "123456"

        onView(withId(R.id.login_activity)).check(matches(isDisplayed()))

        onView(withId(R.id.EmailAddress)).perform(typeText(email))
        closeSoftKeyboard()
        onView(withId(R.id.Password)).perform(typeText(password))
        closeSoftKeyboard()
        onView(withId(R.id.login_button)).perform(click())

        Thread.sleep(3000)
        onView(withId(R.id.main_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withText("Hans")))
        )
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.obligatoriskopgave", appContext.packageName)
    }
}