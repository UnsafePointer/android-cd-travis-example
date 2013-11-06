package com.ruenzuo.weatherapp.fragments;

import java.util.ArrayList;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.ruenzuo.weatherapp.base.CitiesBaseActivity;
import com.ruenzuo.weatherapp.adapters.CitiesAdapter;
import com.ruenzuo.weatherapp.pojos.City;
import com.ruenzuo.weatherapp.pojos.ListCities;
import com.ruenzuo.weatherapp.requests.CityJsonRequest;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class CitiesListFragment extends ListFragment {

    private static final String JSON_CACHE_KEY = "tweets_json";

    private SpiceManager spiceManager;
    private LocationManager locationManager;

    public interface OnCitySelectedListener {
        public void onCityItemSelected(City city);
    }

    public interface OnLoadCitiesListener {
        public void onBeginLoadCities();
        public void onFinishLoadCities(boolean completed);
    }

    private OnCitySelectedListener citySelectedListener;
    private OnLoadCitiesListener loadCitiesListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnCitySelectedListener) {
            citySelectedListener = (OnCitySelectedListener)activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
        if (activity instanceof OnLoadCitiesListener) {
            loadCitiesListener = (OnLoadCitiesListener)activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CitiesBaseActivity citiesBaseActivity = (CitiesBaseActivity) getActivity();
        spiceManager = new SpiceManager(JacksonSpringAndroidSpiceService.class);
        locationManager = (LocationManager) citiesBaseActivity.
                getSystemService(Context.LOCATION_SERVICE);
        ArrayList<City> cities = new ArrayList<City>();
        CitiesAdapter adapter = new CitiesAdapter(getActivity(), cities);
        setListAdapter(adapter);
        refreshLocation();
    }

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        if(spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        citySelectedListener = null;
        loadCitiesListener = null;
    }

    public void refreshLocation() {
        loadCitiesListener.onBeginLoadCities();
        if (Build.PRODUCT.contains("sdk") ||
                Build.PRODUCT.contains("google_sdk")) {
            Location location = new Location("");
            location.setLatitude(-12.043333);
            location.setLongitude(-77.028333);
            refreshCities(location);
        }
        else {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            String provider = locationManager.getBestProvider(criteria, true);
            locationManager.requestLocationUpdates(provider, 0, 0, new CityLocationListener());
        }
    }

    public void refreshCities(Location location) {
        spiceManager.execute(new CityJsonRequest(location),
                JSON_CACHE_KEY,
                DurationInMillis.ALWAYS_EXPIRED,
                new CityRequestListener());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        CitiesAdapter citiesAdapter = (CitiesAdapter) getListAdapter();
        City city = citiesAdapter.getItem(position);
        citySelectedListener.onCityItemSelected(city);
    }

    private class CityRequestListener implements RequestListener<ListCities> {

        public void onRequestFailure(SpiceException spiceException) {
            CitiesAdapter citiesAdapter = (CitiesAdapter)getListAdapter();
            citiesAdapter.clear();
            citiesAdapter.notifyDataSetChanged();
            loadCitiesListener.onFinishLoadCities(false);
        }

        public void onRequestSuccess(ListCities result) {
            CitiesAdapter citiesAdapter = (CitiesAdapter)getListAdapter();
            citiesAdapter.clear();
            for (int i = 0; i < result.getList().size(); i++) {
                City city = result.getList().get(i);
                citiesAdapter.add(city);
            }
            citiesAdapter.setFilterContents(result.getList());
            citiesAdapter.notifyDataSetChanged();
            loadCitiesListener.onFinishLoadCities(true);
        }

    }

    private class CityLocationListener implements LocationListener {

        public void onLocationChanged(Location location) {
            locationManager.removeUpdates(this);
            refreshCities(location);
        }

        public void onProviderDisabled(String provider) {

        }

        public void onProviderEnabled(String provider) {

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

    }

}