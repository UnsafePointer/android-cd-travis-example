package com.ruenzuo.weatherapp.test;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;

import com.ruenzuo.weatherapp.BuildConfig;
import com.ruenzuo.weatherapp.activities.CityActivity;

public class CityActivityFunctionalTests extends ActivityInstrumentationTestCase2<CityActivity> {

    private CityActivity activity;

    public CityActivityFunctionalTests(Class<CityActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
    }

    public void testActivity() throws Throwable {
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(CityActivity.class.getName(), null, false);
        if (BuildConfig.IS_FREE) {
            Thread.sleep(5000);
            ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), activity.getAdView());
            assertNull(activity.getAdView());
        }
    }

}
