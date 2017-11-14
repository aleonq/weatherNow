package com.aniruddhsingh.weathernow.injection;

import android.content.Context;

import com.aniruddhsingh.weathernow.injection.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by taru on 11/15/2017.
 */
@ApplicationScope
@Module
public class AppModule {
    private Context application;

    public AppModule(Context application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }
}