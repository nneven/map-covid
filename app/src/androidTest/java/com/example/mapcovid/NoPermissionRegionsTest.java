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
public class NoPermissionRegionsTest {
    @Rule public ActivityScenarioRule<MapsActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MapsActivity.class);

    @Test
    public void mapRegionsNoLocationAccess() throws InterruptedException {
        onView(withId(R.id.map));
        Thread.sleep(10000);
    }
    //Before, running the test go to settings and do not give access to your location
    //Then, run test and it should take you to the map
    //Adjust screen to show the blue region
    //Manually click on the blue region
    //A pop-up should appear on the bottom with the following text
    //University Park
    //Cases: 3395
    //Deaths: 51
}

