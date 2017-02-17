package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Created by ${farhanarnob} on ${06-Oct-16}.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthQuake>> {
    public static final String TAG_NAME = EarthquakeLoader.class.getName();
    String USGS_REQUEST_URL;

    public EarthquakeLoader(Context context, String USGS_REQUEST_URL) {
        super(context);
        this.USGS_REQUEST_URL = USGS_REQUEST_URL;
    }

    @Override
    public List<EarthQuake> loadInBackground() {
        Log.d(TAG_NAME, "loadInBackground");
        if (USGS_REQUEST_URL == null) {
            return null;
        }
        List<EarthQuake> earthQuakes = QueryUtils.fetchEarthquakeData(USGS_REQUEST_URL);
        return earthQuakes;
    }

    @Override
    protected void onStartLoading() {
        Log.d(TAG_NAME, "onStartLoading");

        forceLoad();
    }
}
