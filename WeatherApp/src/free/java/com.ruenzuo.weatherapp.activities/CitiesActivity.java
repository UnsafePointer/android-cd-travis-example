package com.ruenzuo.weatherapp.activities;

import android.os.Bundle;
import android.widget.FrameLayout;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.base.CitiesBaseActivity;
import com.google.ads.AdView;

public class CitiesActivity extends CitiesBaseActivity {

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adView = new AdView(this, AdSize.BANNER, "a14defa59f9a1e7");
        FrameLayout layout = (FrameLayout)findViewById(R.id.frameLayout);
        layout.addView(adView);
        adView.loadAd(new AdRequest());
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

}