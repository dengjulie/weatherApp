package com.juliedeng.weatherapp;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ViewPager fragmentPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentPager = findViewById(R.id.viewPager);
        fragmentPager.setAdapter(new WeatherAdapter(getSupportFragmentManager()));
    }
}
