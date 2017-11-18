package com.aniruddhsingh.weathernow.main.mvp;

import com.aniruddhsingh.weathernow.beans.WeatherData;
import com.aniruddhsingh.weathernow.util.Constants;

/**
 * Created by taru on 11/14/2017.
 */

public class MainActivityPresenter implements MainActivityMvp.Presenter {

    private MainActivityMvp.Model model;
    MainActivityMvp.View view;
    private String city;

    public MainActivityPresenter(MainActivityMvp.Model model) {
        this.model = model;
        model.setPresenter(this);
    }

    @Override
    public void setView(MainActivityMvp.View view) {
        this.view = view;
    }

    @Override
    public void requestWeatherData(String city) {
        view.showProgress();
        model.requestForData(city);
    }

    @Override
    public void notifyData(WeatherData weatherData, String city) {
        this.city = city;
        view.hideProgress();
        // Construct Icon Url from weatherData
        try {
            String icon = weatherData.getWeather().get(0).getIcon();
            String url = Constants.URL_WEATHER_ICON + icon + Constants.ICON_TYPE;
            view.loadWeatherIcon(url);
        } catch (IndexOutOfBoundsException e) {
            // In case weather[] is empty
            view.displayDefaultIcon();
        }
        model.saveCity(city);
        view.displayData(weatherData);
    }

    @Override
    public void notifyError(String exception) {
        view.hideProgress();
        view.showError(exception);
    }

    @Override
    public void openDetailsButtonClicked() {
        String url = String.format(Constants.URL_WEATHER_HTML, city);
        view.openDetailsExternally(url);
    }
}