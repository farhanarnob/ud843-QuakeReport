package com.example.android.quakereport;

/**
 * Created by ${farhanarnob} on ${06-Oct-16}.
 */

public class EarthQuake {
    private double mMag;
    private String mPlace;
    private long mTimeMilliseconds;

    /**
     * constructs a new {@link EarthQuake} object
     *
     * @param mag magnitude of of the earthquake
     * @param place location of the city location of the the earthquake
     * @param TimeMilliseconds time in milliseconds
     */
    public EarthQuake(double mag, String place, long TimeMilliseconds){
        mMag = mag;
        mPlace = place;
        mTimeMilliseconds = TimeMilliseconds;
    }

    public Double getMag() {
        return (mMag);
    }

    public String getPlace() {
        return mPlace;
    }

    public long getDateAndTime() {
        return mTimeMilliseconds;
    }
}
