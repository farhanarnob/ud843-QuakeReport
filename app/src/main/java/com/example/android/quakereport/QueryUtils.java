package com.example.android.quakereport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${farhanarnob} on ${06-Oct-16}.
 */

public final class QueryUtils {
    private QueryUtils(){

    }
    /**
     * Return a list of {@link EarthQuake} objects that has been built up from
     * parsing a JSON response.
     */

    public static List<EarthQuake> fetchEarthquakeData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = makeHttpRequest(url);
        List<EarthQuake> earthQuakes = extractEarthquakes(jsonResponse);
        return earthQuakes;
    }

    private static String makeHttpRequest(URL url) {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /*milliseconds*/);
            urlConnection.setConnectTimeout(15000/*milliseconds*/);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                String line = bufferedReader.readLine();
                while (line != null) {
                    output.append(line);
                    line = bufferedReader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return output.toString();
    }

    private static ArrayList<EarthQuake> extractEarthquakes(String jsonResponse) {
        //Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<EarthQuake> earthQuakes = new ArrayList<>();
        //try to parse SAMPLE_JSON_RESPONSE. If problem JSONException exception object well be thrown
        try{
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray features =root.getJSONArray("features");
            for(int i =0 ;i<features.length();i++){
                JSONObject earthquake = features.getJSONObject(i);
                JSONObject properties = earthquake.getJSONObject("properties");
                double mag = properties.getDouble("mag");
                String place = properties.getString("place");
                long time = properties.getLong("time");
                String url = properties.getString("url");
                earthQuakes.add(new EarthQuake(mag,place,time,url));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return earthQuakes;
    }

    /**
     * return url from url string
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }



}
