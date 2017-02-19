package com.example.android.quakereport;

/**
 * Created by ${farhanarnob} on ${06-Oct-16}.
 */

public class EarthQuake {
    private double mMag;
    private String mPlace;
    private long mTimeMilliseconds;
    private String mUrl;

    /**
     * constructs a new {@link EarthQuake} object
     *
     * @param mag magnitude of of the earthquake
     * @param place location of the city location of the the earthquake
     * @param TimeMilliseconds time in milliseconds
     * @param url for detail in website
     */
    public EarthQuake(double mag, String place, long TimeMilliseconds, String url){
        mMag = mag;
        mPlace = place;
        mTimeMilliseconds = TimeMilliseconds;
        mUrl = url;
    }

    public Double getMag() {
        if (mMag != 0) {
            return (mMag);
        } else {
            return Double.valueOf(0);
        }
    }

    public String getPlace() {
        return mPlace;
    }

    public long getDateAndTime() {
        return mTimeMilliseconds;
    }

    public String getUrl(){
        return mUrl;
    }
}
