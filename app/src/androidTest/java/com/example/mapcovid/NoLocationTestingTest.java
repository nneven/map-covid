package com.example.mapcovid;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class NoLocationTestingTest {
    @Rule public ActivityScenarioRule<MapsActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MapsActivity.class);

    @Test
    public void ViewPopUpPinWithoutLocation() throws InterruptedException {

        onView(withId(R.id.map)).perform();
        Thread.sleep(40000);

    }
    //Before running, manually go to settings and do not allow location
    //Then run the map
    // Manually move map to see a Testing Location pin
    //Manually click on the pin
    //A pop up should appear from the pin that shows the Testing Center name and its hours
}

