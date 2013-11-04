package com.ruenzuo.weatherapp.adapters;

import java.util.ArrayList;
import java.util.List;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.pojos.City;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class CitiesAdapter extends ArrayAdapter<City> implements Filterable {

    static class ViewHolder {
        public TextView textCityName;
        public TextView textCityLocation;
    }

    private final Context context;
    private ArrayList<City> cities;
    private ArrayList<City> filteredCities;

    public CitiesAdapter(Context context, ArrayList<City> cities) {
        super(context, R.layout.row_city, cities);
        this.context = context;
        this.cities = new ArrayList<City>(cities);
        this.filteredCities = new ArrayList<City>(cities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_city, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textCityName = (TextView) rowView.findViewById(R.id.txtViewCityName);
            viewHolder.textCityLocation = (TextView) rowView.findViewById(R.id.txtViewCityLocation);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        if (position < filteredCities.size()) {
            City city = filteredCities.get(position);
            holder.textCityName.setText(city.getName());
            holder.textCityLocation.setText("Lat: " + String.format("%.2f", city.getCoord().getLat()) + ", " +
                    "Lon: " + String.format("%.2f", city.getCoord().getLon()));
        }
        return rowView;
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {
                filteredCities = (ArrayList<City>)results.values;
                clear();
                for (int i = 0; i < filteredCities.size(); i++) {
                    City city = filteredCities.get(i);
                    add(city);
                }
                notifyDataSetChanged();
            }

            @SuppressLint("DefaultLocale")
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<City> filteredList = new ArrayList<City>();
                if (constraint == null || constraint.length() == 0) {
                    results.count = cities.size();
                    results.values = cities;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < cities.size(); i++) {
                        City city = cities.get(i);
                        if (city.getName().toLowerCase().startsWith(constraint.toString())) {
                            filteredList.add(cities.get(i));
                        }
                    }
                    results.count = filteredList.size();
                    results.values = filteredList;
                }
                return results;
            }
        };
        return filter;
    }

    public void setFilterContents(List<City> list) {
        this.cities = new ArrayList<City>(list);
        this.filteredCities = new ArrayList<City>(list);
    }

}