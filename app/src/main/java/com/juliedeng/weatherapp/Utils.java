package com.juliedeng.weatherapp;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by juliedeng on 3/8/18.
 */

public class Utils {

    public static final String API_KEY = "4a0c5daa797342c9dc91ef493f6ac407";
    public static final String URL_BASE_STRING = "https://api.darksky.net/forecast/";

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    static class JSONRequestTask extends AsyncTask<Object, Void, JSONObject> {
        View view;
        String latitude;
        String longitude;
        String location;

        public JSONRequestTask(String latitude, String longitude, String location, View view) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.location = location;
            this.view = view;
        }

        @Override
        protected JSONObject doInBackground(Object... viewAndLocation) {
            String urlString = URL_BASE_STRING + API_KEY + "/" + latitude + "," + longitude;
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                InputStream in = new BufferedInputStream(conn.getInputStream());
                String response = convertStreamToString(in);
                JSONObject json = new JSONObject(response);
                return json;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                JSONObject todayData = (JSONObject) jsonObject.getJSONObject("daily").getJSONArray("data").get(0);
                String weatherIcon = todayData.getString("icon");
                String temperatureLow = todayData.getString("temperatureMin");
                String temperatureHigh = todayData.getString("temperatureMax");
                String description = todayData.getString("summary");
                String rainProb = todayData.getString("precipProbability");
                String rainSummary = jsonObject.getJSONObject("hourly").getString("summary");



                TextView mTemp, mDescription, mLocation, mRain;
                ImageView mIcon;
                mTemp = view.findViewById(R.id.high_low);
                mDescription = view.findViewById(R.id.description);
                mLocation = view.findViewById(R.id.city_state);
                mRain = view.findViewById(R.id.rain);
                mIcon = view.findViewById(R.id.icon);

                mTemp.setText(temperatureHigh + "/" + temperatureLow);
                mDescription.setText(description);
                mRain.setText(rainProb + "/n" + rainSummary);
                mLocation.setText(location);
                switch (weatherIcon) {
                    case "rain":
                        mIcon.setImageResource(R.drawable.ic_launcher_background);
                        break;
                    default:
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.onPostExecute(jsonObject);
        }
    }
}
