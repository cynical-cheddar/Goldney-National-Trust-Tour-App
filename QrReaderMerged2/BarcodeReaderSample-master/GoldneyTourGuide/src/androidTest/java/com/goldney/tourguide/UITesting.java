package com.goldney.tourguide;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

@RunWith(AndroidJUnit4.class)

@LargeTest
public class UITesting {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void changeText_sameActivity() {

        //Espresso.onView()



        //Espresso.onView(withId(R.id.btn_continue)).perform(click())

//        // Type text and then press the button.
//        onView(withId(R.id.editTextUserInput))
//                .perform(typeText(stringToBetyped), closeSoftKeyboard());
//        onView(withId(R.id.changeTextBt)).perform(click());
//
//        // Check that the text was changed.
//        onView(withId(R.id.textToBeChanged))
//                .check(matches(withText(stringToBetyped)));
    }
}
