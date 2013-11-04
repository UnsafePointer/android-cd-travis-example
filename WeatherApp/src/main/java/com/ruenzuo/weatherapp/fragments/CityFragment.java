package com.ruenzuo.weatherapp.fragments;

import java.util.ArrayList;

import com.ruenzuo.weatherapp.adapters.CityAdapter;
import com.ruenzuo.weatherapp.pojos.City;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

public class CityFragment extends ListFragment {

    private City city;
    final String DEGREE  = "\u00b0";

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> values = new ArrayList<String>();
        values.add(String.format("%.2f ", city.getMain().getTemp()) + DEGREE + "K");
        values.add(String.format("%.2f hpa", city.getMain().getPressure()));
        values.add(String.format("%.2f %%", city.getMain().getHumidity()));
        CityAdapter adapter = new CityAdapter(getActivity(), values);
        setListAdapter(adapter);
    }

}