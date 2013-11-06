package com.ruenzuo.weatherapp.activities;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.base.CityBaseActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class CityActivity extends CityBaseActivity {

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