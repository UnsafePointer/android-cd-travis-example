package com.ruenzuo.weatherapp.requests;

import android.location.Location;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.ruenzuo.weatherapp.pojos.ListCities;

public class CityJsonRequest extends SpringAndroidSpiceRequest<ListCities> {

    private Location location;

    public CityJsonRequest(Location location) {
        super(ListCities.class);
        this.location = location;
    }

    @Override
    public ListCities loadDataFromNetwork() throws Exception {
        return getRestTemplate().
                getForObject("http://api.openweathermap.org/data/2.5/find?lat=" + location.getLatitude() +
                        "&lon=" + location.getLongitude() +
                        "&cnt=100&type=json",
                        ListCities.class);
    }

}