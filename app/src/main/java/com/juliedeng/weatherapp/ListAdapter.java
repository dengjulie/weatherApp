package com.juliedeng.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by juliedeng on 3/9/18.
 */

public class ListAdapter extends RecyclerView.Adapter<WeatherAdapter.CustomViewHolder> {

    Context context;
    ArrayList<WeatherDay> weatherDays = new ArrayList<>();

    ListAdapter(ArrayList<WeatherDay> weatherDays, Context context) {
        this.context = context;
        this.weatherDays = weatherDays;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.row_view,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.bind(weatherDays.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherDays.size();
    }

    public void setPokemons(ArrayList<WeatherDay> weatherDays) {
        this.weatherDays = weatherDays;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView day;

        public CustomViewHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
        }

        void bind(WeatherDay weatherDay) {
            day.setText(weatherDay.getWeatherDay());

            final String weatherDay = weatherDay.getWeatherDay();

//            Glide.with(context).load("http://img.pokemondb.net/artwork/" + pokemon.getName().toLowerCase() + ".jpg").into(avatar);
        }
    }

}
