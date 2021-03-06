package com.mck.quicktemps.adapter;

import android.graphics.Bitmap;
import android.view.View;

import com.mck.quicktemps.WeatherDataHelper;
import com.mck.quicktemps.asynctask.GetWeatherIconAsyncTask;
import com.mck.quicktemps.model.CurrentWeatherData;

import java.util.List;

/**
 * Holder of CurrentViewData for display in RecyclerView.Adapter
 * Created by Michael on 7/8/2016.
 */
class CurrentViewHolder extends WeatherViewHolder implements GetWeatherIconAsyncTask.Callback {
    public static final String TAG = "CurrentViewHolder";

    CurrentViewHolder(View view, WeatherViewRecyclerViewAdapter adapter) {
        super(view, adapter);
        detailsAreVisible = true;
    }

    @Override
    public void onWeatherIconResult(Bitmap icon, Integer requestId) {
        ivIcon.setImageBitmap(icon);
    }

    void onBindViewHolder(CurrentWeatherData currentWeatherData, List<Object> payloads) {
        super.bindViewHolder(payloads);

        if (payloads != null && payloads.contains(ON_CLICK)){
            return;
        }

        // tvTime
        String currentTime = WeatherDataHelper.formatTime(System.currentTimeMillis()/1000);
        tvTime.setText(currentTime);
        // tvGeneratedTime
        if (currentWeatherData.dt != null){
            String formattedTime = WeatherDataHelper.formatTimeAmPm(currentWeatherData.dt);
            tvGeneratedTime.setText(formattedTime);
            tvGeneratedTime.setVisibility(View.VISIBLE);
        }

        // tvTemperature,
        if (currentWeatherData.main.temp != null) {
            String formattedTemperature = WeatherDataHelper.formatTemperature(currentWeatherData.main.temp);
            tvTemperature.setText(formattedTemperature);
        }

        // tvTemperatureHighLow,
        if (currentWeatherData.main.temp_max != null &&
                currentWeatherData.main.temp_min != null){
            String formattedTemperatureHigh = WeatherDataHelper.formatTemperature(currentWeatherData.main.temp_max);
            String formattedTemperatureLow = WeatherDataHelper.formatTemperature(currentWeatherData.main.temp_min);
            String result = formattedTemperatureHigh + "/" + formattedTemperatureLow;
            tvTemperatureHighLow.setText(result);
        }

        // tvDescription,
        if (currentWeatherData.weather.length > 0 &&
                currentWeatherData.weather[0].description != null){
            tvDescription.setText(
                currentWeatherData.weather[0].description.toUpperCase());
        }

        // Extra data

        // tvWindSpeed,
        if (currentWeatherData.wind != null &&
                currentWeatherData.wind.speed != null){
            String formattedSpeed = WeatherDataHelper.formatSpeed(currentWeatherData.wind.speed);
            tvWindSpeed.setText(formattedSpeed);
            layoutWind.setVisibility(View.VISIBLE);
        } else {
            layoutWind.setVisibility(View.GONE);
        }
        // tvWindDirection;
        if (currentWeatherData.wind != null &&
                currentWeatherData.wind.deg != null){
            String formattedDirection = WeatherDataHelper.formatDirection(currentWeatherData.wind.deg);
            tvWindDirection.setText(formattedDirection);
            tvWindDirection.setVisibility(View.VISIBLE);
        } else {
            tvWindDirection.setVisibility(View.GONE);
        }

        // tvHumidity,
        if (currentWeatherData.main.humidity != null){
            String formattedPercent = WeatherDataHelper.formatPercent(currentWeatherData.main.humidity);
            tvHumidity.setText(formattedPercent);
            tvHumidity.setVisibility(View.VISIBLE);
            tvLabelHumidity.setVisibility(View.VISIBLE);
        } else {
            tvHumidity.setVisibility(View.GONE);
            tvLabelHumidity.setVisibility(View.GONE);
        }
        // tvCloudiness,
        if (currentWeatherData.clouds != null && currentWeatherData.clouds.all != null ){
            String formattedPercent = WeatherDataHelper.formatPercent(currentWeatherData.clouds.all);
            tvCloudiness.setText(formattedPercent);
            tvCloudiness.setVisibility(View.VISIBLE);
            tvLabelCloudiness.setVisibility(View.VISIBLE);
        } else {
            tvCloudiness.setVisibility(View.GONE);
            tvLabelCloudiness.setVisibility(View.GONE);
        }

        GetWeatherIconAsyncTask task = new GetWeatherIconAsyncTask(
                mView.getContext(), this,
                getAdapterPosition(),
                currentWeatherData.weather[0].icon);
        task.execute();

    }
}
