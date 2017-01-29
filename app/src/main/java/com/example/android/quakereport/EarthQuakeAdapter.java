package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ${farhanarnob} on ${06-Oct-16}.
 */

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {
    public EarthQuakeAdapter(Context context,  ArrayList<EarthQuake> earthQuakes) {
        super(context, 0, earthQuakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        EarthQuake earthQuake = getItem(position);

        TextView mag_view = (TextView) listItemView.findViewById(R.id.mag);
        mag_view.setText(earthQuake.getMag());

        TextView place_view = (TextView) listItemView.findViewById(R.id.place);
        place_view.setText(earthQuake.getPlace());

        TextView time_view = (TextView) listItemView.findViewById(R.id.time_of_earth_quack);
        time_view.setText(earthQuake.getTime());


        return listItemView;
    }
}
