package com.juliedeng.weatherapp;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        public JSONRequestTask(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
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
                String weatherIcon = ((JSONObject) (jsonObject.getJSONObject("daily").getJSONArray("data").get(0))).getString("icon");
                String temperatureLow = ((JSONObject) (jsonObject.getJSONObject("daily").getJSONArray("data").get(0))).getString("temperatureMin");
                String temperatureHigh = ((JSONObject) (jsonObject.getJSONObject("daily").getJSONArray("data").get(0))).getString("temperatureMax");
                String description = ((JSONObject) (jsonObject.getJSONObject("daily").getJSONArray("data").get(0))).getString("summary");
                String rainProb = ((JSONObject) (jsonObject.getJSONObject("daily").getJSONArray("data").get(0))).getString("precipProbability");
                String rainSummary = ((JSONObject) (jsonObject.getJSONObject("hourly").getString("summary");

                ((TextView) view.findViewById(R.id.high_low)).setText(temperatureHigh + "/" + temperatureLow);
                ((TextView) view.findViewById(R.id.description)).setText(description);
                ((TextView) view.findViewById(R.id.precipprob)).setText(precipProb);
                ((TextView) view.findViewById(R.id.preciptype)).setText(precipType);
                ((TextView) view.findViewById(R.id.preciptime)).setText(s);
                String icon = ((JSONObject) (jsonObject.getJSONObject("daily").getJSONArray("data").get(0))).getString("icon");
                ImageView weatherIcon = view.findViewById(R.id.weathericon);

                ((TextView) view.findViewById(R.id.day)).setText(jsonObject.getJSONObject("currently").getString("temperature"));

            } catch (Exception e) {
            }
            super.onPostExecute(jsonObject);
        }
    }
}
