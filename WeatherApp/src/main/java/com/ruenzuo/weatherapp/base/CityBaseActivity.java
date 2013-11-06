package com.ruenzuo.weatherapp.base;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.fragments.CityFragment;
import com.ruenzuo.weatherapp.pojos.City;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public class CityBaseActivity extends ActionBarActivity {

    public static final String EXTRA_CITY = "city";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            City city = (City) extras.getSerializable(EXTRA_CITY);
            CityFragment fragment = (CityFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.cityFragment);
            try {
                City clone = (City) city.clone();
                fragment.setCity(clone);
                setTitle(clone.getName());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}