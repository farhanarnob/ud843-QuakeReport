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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<EarthQuake> earthquakes = new ArrayList<>();
        earthquakes.add(new EarthQuake(2.5,"Dhaka","Feb 2, 2016"));
        earthquakes.add(new EarthQuake(5.3,"San Francisco","May 15, 2016"));
        earthquakes.add(new EarthQuake(3.4,"London","July 4, 2016"));
        earthquakes.add(new EarthQuake(7.1,"Moscow","Jan 6, 2016"));
        earthquakes.add(new EarthQuake(4.1,"Paris","Aug 19, 2016"));
        earthquakes.add(new EarthQuake(3.7,"Derjiling","April 16, 2016"));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthQuakeAdapter  earthQuakeAdapter = new EarthQuakeAdapter(this,earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(earthQuakeAdapter);
    }
}
