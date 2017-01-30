package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ${farhanarnob} on ${06-Oct-16}.
 */

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {
    private static  final  String LOCATION_SEPARATOR = " of ";
    private String offsetPlace = String.valueOf(R.string.near_the);
    private String mainPlace = "";
    GradientDrawable magnitudeCircle;
    TextView mag_view;
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

        final EarthQuake earthQuake = getItem(position);

        String magnitudeToDisplay = formatMagnitude(earthQuake.getMag());

        mag_view = (TextView) listItemView.findViewById(R.id.mag);
        mag_view.setText(magnitudeToDisplay);
        magnitudeCircle =(GradientDrawable) mag_view.getBackground();
        int magnitudeColorID = getMagnitudeColor(earthQuake.getMag());
        int magnitudeColor = ContextCompat.getColor(getContext(),magnitudeColorID);
        magnitudeCircle.setColor(magnitudeColor);

        String place = earthQuake.getPlace();

        String spitedPlace[] = place.split(LOCATION_SEPARATOR);
        if(spitedPlace.length<2){
            mainPlace = place;
        }
        else {
            offsetPlace = spitedPlace[0]+LOCATION_SEPARATOR;
            mainPlace = spitedPlace[1];
        }
        TextView offset_place = (TextView) listItemView.findViewById(R.id.offset_place);
        offset_place.setText(offsetPlace);

        TextView place_view = (TextView) listItemView.findViewById(R.id.primary_place);
        place_view.setText(mainPlace);

        Date dateObject = new Date(earthQuake.getDateAndTime());

        TextView date_view = (TextView) listItemView.findViewById(R.id.date_of_the_earth_quake);

        String dateToDisplay = formatDate(dateObject);
        date_view.setText(dateToDisplay);

        TextView time_view = (TextView) listItemView.findViewById(R.id.time_of_earth_quake);
        String timeToDisplay = formatTime(dateObject);
        time_view.setText(timeToDisplay);

        // This listening idea is implemented by me.
//        listItemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = earthQuake.getUrl();
//                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
//                i.setData(Uri.parse(url));
//                getContext().startActivity(i);
//            }
//        });
        return listItemView;
    }

    private int getMagnitudeColor(double magnitudeToDisplay) {
        int magInt = (int)Math.floor(magnitudeToDisplay);
        switch (magInt){
            case 0:
            case 1:
                return R.color.magnitude1;
            case 2:
                return R.color.magnitude2;
            case 3:
                return R.color.magnitude3;
            case 4:
                return R.color.magnitude4;
            case 5:
                return R.color.magnitude5;
            case 6:
                return R.color.magnitude6;
            case 7:
                return R.color.magnitude7;
            case 8:
                return R.color.magnitude8;
            case 9:
                return R.color.magnitude9;
        }
        return R.color.magnitude10plus;
    }

    private String formatMagnitude(Double magnitude){
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    private String formatTime(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateObject);
    }
}
