package com.ruenzuo.weatherapp.activities;

import com.ruenzuo.weatherapp.R;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v4.view.MenuItemCompat;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.LinearLayout;

import com.ruenzuo.weatherapp.adapters.CitiesAdapter;
import com.ruenzuo.weatherapp.fragments.CitiesListFragment;
import com.ruenzuo.weatherapp.pojos.City;

public class CitiesActivity extends ActionBarActivity implements CitiesListFragment.OnCitySelectedListener,
        CitiesListFragment.OnLoadCitiesListener, SearchView.OnQueryTextListener {

    private LinearLayout progressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        CitiesListFragment fragment = (CitiesListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.citiesListFragment);
        fragment.getListView().setTextFilterEnabled(true);
        progressLayout = (LinearLayout) findViewById(R.id.progressLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cities, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        setupSearchView(searchView);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            CitiesListFragment fragment = (CitiesListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.citiesListFragment);
            fragment.refreshLocation();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupSearchView(SearchView searchView) {
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search cities");
        CitiesListFragment fragment = (CitiesListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.citiesListFragment);
        fragment.getListView().setTextFilterEnabled(true);
    }

    public boolean onQueryTextChange(String newText) {
        CitiesListFragment fragment = (CitiesListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.citiesListFragment);
        CitiesAdapter citiesAdapter = (CitiesAdapter)fragment.getListView()
                .getAdapter();
        if (TextUtils.isEmpty(newText)) {
            citiesAdapter.getFilter().filter("");
        } else {
            citiesAdapter.getFilter().filter(newText);
        }
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    public void onCityItemSelected(City city) {
        Intent intent = new Intent(getApplicationContext(), CityActivity.class);
        intent.putExtra(CityActivity.EXTRA_CITY, city);
        startActivity(intent);
    }

    @Override
    public void onBeginLoadCities() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishLoadCities(boolean completed) {
        progressLayout.setVisibility(View.GONE);
    }

}