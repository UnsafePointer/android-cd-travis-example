package com.ruenzuo.weatherapp.adapters;

import java.util.ArrayList;
import java.util.Arrays;

import com.ruenzuo.weatherapp.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CityAdapter extends ArrayAdapter<String> {

    static class ViewHolder {
        public TextView textTitle;
        public TextView textValue;
    }

    private final Context context;
    private ArrayList<String> values;
    private ArrayList<String> titles;

    public CityAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.row_city, values);
        this.context = context;
        this.values = new ArrayList<String>(values);
        String[] titles = context.getResources().getStringArray(R.array.titles);
        this.titles = new ArrayList<String>(Arrays.asList(titles));
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_value, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textValue = (TextView)rowView.findViewById(R.id.txtViewValue);
            viewHolder.textTitle = (TextView)rowView.findViewById(R.id.txtViewValueTitle);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        if (position < values.size()) {
            holder.textValue.setText(values.get(position));
            holder.textTitle.setText(titles.get(position));
        }
        return rowView;
    }

}