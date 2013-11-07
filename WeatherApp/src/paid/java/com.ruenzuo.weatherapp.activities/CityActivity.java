package com.ruenzuo.weatherapp.activities;

import com.ruenzuo.weatherapp.base.CityBaseActivity;
import android.os.Bundle;
import android.view.View;

public class CityActivity extends CityBaseActivity {

    private View mAdView = null;

    public View getAdView() {
        return mAdView;
    }

    public void setAdView(View adView) {
        this.mAdView = adView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}