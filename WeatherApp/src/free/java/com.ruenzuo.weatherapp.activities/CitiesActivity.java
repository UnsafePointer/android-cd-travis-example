package com.ruenzuo.weatherapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.base.CitiesBaseActivity;
import com.google.ads.AdView;

public class CitiesActivity extends CitiesBaseActivity {

    private AdView adView;

    private View mAdView;

    public View getAdView() {
        return mAdView;
    }

    public void setAdView(View adView) {
        this.mAdView = adView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adView = new AdView(this, AdSize.BANNER, "a14defa59f9a1e7");
        FrameLayout layout = (FrameLayout)findViewById(R.id.frameLayout);
        layout.addView(adView);
        adView.loadAd(new AdRequest());
        setAdView(adView);
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

}