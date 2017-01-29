package com.example.android.quakereport;

/**
 * Created by ${farhanarnob} on ${06-Oct-16}.
 */

public class EarthQuake {
    private double mMag;
    private String mPlace, mTime;
    public EarthQuake(double mag, String place, String time){
        mMag = mag;
        mPlace = place;
        mTime = time;
    }

    public String getMag() {
        return (Double.toString(mMag));
    }

    public String getPlace() {
        return mPlace;
    }

    public String getTime() {
        return mTime;
    }
}
