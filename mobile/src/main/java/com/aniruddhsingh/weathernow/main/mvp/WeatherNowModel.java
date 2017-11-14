package com.aniruddhsingh.weathernow.main.mvp;

import android.content.Context;

import com.aniruddhsingh.weathernow.App;
import com.aniruddhsingh.weathernow.R;
import com.aniruddhsingh.weathernow.beans.WeatherData;
import com.aniruddhsingh.weathernow.network.IRequest;
import com.aniruddhsingh.weathernow.util.Util;

/**
 * Created by taru on 11/14/2017.
 */

public class WeatherNowModel implements MainActivityMvp.Model {

    private Context context;
    private IRequest request;
    private MainActivityMvp.Presenter presenter;

    public WeatherNowModel(IRequest request, Context context) {
        this.context = context;
        this.request = request;
        request.setModel(this);
    }

    @Override
    public void setPresenter(MainActivityMvp.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestForData(String city) {
        if (city == null || city.isEmpty()) {
            city = Util.getLastSearchedCity(context);
            if (city == null) {
                presenter.notifyError(context.getString(R.string.error_invalid_city));
                return;
            }
        }
        request.sendRequest(context, city);
    }

    @Override
    public void setData(WeatherData weatherNowdata, String city) {
        presenter.notifyData(weatherNowdata, city);
    }

    @Override
    public void setError(String exception) {
        presenter.notifyError(exception);
    }

    @Override
    public void saveCity(String city) {
        Util.setLastSearchedCity(context, city);
    }
}