package com.aniruddhsingh.weathernow;


import com.aniruddhsingh.weathernow.injection.AppModule;
import com.aniruddhsingh.weathernow.injection.NetworkModule;
import com.aniruddhsingh.weathernow.injection.WeatherPageModule;
import com.aniruddhsingh.weathernow.injection.scopes.ApplicationScope;
import com.aniruddhsingh.weathernow.main.MainActivity;

import dagger.Component;

/**
 * Created by taru on 11/14/2017.
 */
@ApplicationScope
@Component(modules = {AppModule.class, WeatherPageModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity target);

}