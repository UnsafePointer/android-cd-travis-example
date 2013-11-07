package com.ruenzuo.weatherapp.activities;

import android.os.Bundle;
import android.view.View;

import com.ruenzuo.weatherapp.base.CitiesBaseActivity;

public class CitiesActivity extends CitiesBaseActivity {

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