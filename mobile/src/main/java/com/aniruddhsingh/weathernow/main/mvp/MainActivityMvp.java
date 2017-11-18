package com.aniruddhsingh.weathernow.main.mvp;

import com.aniruddhsingh.weathernow.beans.WeatherData;

/**
 * Created by taru on 11/14/2017.
 */

public interface MainActivityMvp {
    interface Model {
        void setPresenter(Presenter presenter);

        void requestForData(String city);

        void setData(WeatherData weatherNowdata, String city);

        void setError(String exception);

        void saveCity(String city);
    }

    interface View {
        void openDetailsExternally(String url);

        void loadWeatherIcon(String url);

        void displayDefaultIcon();

        void displayData(WeatherData data);

        void showError(String error);

        void showProgress();

        void hideProgress();
    }

    interface Presenter {
        void setView(View view);

        void requestWeatherData(String city);

        void notifyData(WeatherData weatherData, String city);

        void notifyError(String exception);

        void openDetailsButtonClicked();
    }
}
