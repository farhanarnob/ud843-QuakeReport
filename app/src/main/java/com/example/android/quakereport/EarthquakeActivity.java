/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthQuake>> {
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;
    String USGS_REQUEST_URL;
    private EarthQuakeAdapter earthQuakeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        USGS_REQUEST_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
//        EarthQuakeAsyncTask earthQuakeAsyncTask = new EarthQuakeAsyncTask();
//        earthQuakeAsyncTask.execute(USGS_REQUEST_URL);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        earthQuakeAdapter = new EarthQuakeAdapter(this, new ArrayList<EarthQuake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(earthQuakeAdapter);
        // it is used by udacity's instructor
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EarthQuake currentEarthquake = earthQuakeAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent);

            }
        });
        getSupportLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<EarthQuake>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthQuake>> loader, List<EarthQuake> data) {
        earthQuakeAdapter.clear();
        if (data != null && !data.isEmpty()) {
            earthQuakeAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<EarthQuake>> loader) {
        earthQuakeAdapter.clear();
    }

//    private class EarthQuakeAsyncTask extends AsyncTask<String, Void, List<EarthQuake>> {
//
//        @Override
//        protected List<EarthQuake> doInBackground(String... stringUrl) {
//            if (stringUrl.length < 1 || stringUrl[0] == null) {
//                return null;
//            }
//            List<EarthQuake> earthQuakes = QueryUtils.fetchEarthquakeData(stringUrl[0]);
//            return earthQuakes;
//        }
//
//        @Override
//        protected void onPostExecute(List<EarthQuake> earthQuakes) {
//
//            if (earthQuakes == null) {
//                return;
//            }
//            if(earthQuakeAdapter!=null){
//                earthQuakeAdapter.clear();
//            }
////            earthQuakeAdapter.clear();
//            earthQuakeAdapter.clear();
//            earthQuakeAdapter.addAll(earthQuakes);
//            super.onPostExecute(earthQuakes);
//        }
//
//
//    }
}
