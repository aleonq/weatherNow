package com.aniruddhsingh.weathernow.injection;

import android.content.Context;

import com.aniruddhsingh.weathernow.injection.scopes.ApplicationScope;
import com.aniruddhsingh.weathernow.main.mvp.MainActivityMvp;
import com.aniruddhsingh.weathernow.main.mvp.MainActivityPresenter;
import com.aniruddhsingh.weathernow.main.mvp.WeatherNowModel;
import com.aniruddhsingh.weathernow.network.IRequest;

import dagger.Module;
import dagger.Provides;

/**
 * Created by taru on 11/15/2017.
 */
@ApplicationScope
@Module(includes = {AppModule.class, NetworkModule.class})
public class WeatherPageModule {
    @Provides
    public MainActivityMvp.Presenter getProductListPresenter(MainActivityMvp.Model model) {
        return new MainActivityPresenter(model);
    }

    @Provides
    public MainActivityMvp.Model getProductListModel(IRequest request, Context context) {
        return new WeatherNowModel(request, context);
    }
}