package com.ruenzuo.weatherapp.test;

import android.app.Instrumentation;
import android.support.v4.app.ListFragment;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.ListView;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.activities.CitiesActivity;
import com.ruenzuo.weatherapp.activities.CityActivity;

public class CitiesActivityFunctionalTests extends ActivityInstrumentationTestCase2 <CitiesActivity> {

    private CitiesActivity activity;

    public CitiesActivityFunctionalTests() {
        super(CitiesActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
    }

    public void testStartSecondActivity() throws Throwable {
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(CityActivity.class.getName(), null, false);
        Thread.sleep(10000);
        runTestOnUiThread(new Runnable() {
            public void run() {
                ListFragment listFragment = (ListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.citiesListFragment);
                ListView listView = listFragment.getListView();
                ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), listView);
                listView.performItemClick(listView.getAdapter().getView(3, null, null), 3, listView.getItemIdAtPosition(3));
            }
        });
        CityActivity startedActivity = (CityActivity) monitor.waitForActivityWithTimeout(4000);
        assertNotNull(startedActivity);
    }

}
