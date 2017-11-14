package com.aniruddhsingh.weathernow.injection;

import android.content.Context;

import com.aniruddhsingh.weathernow.injection.scopes.ApplicationScope;
import com.aniruddhsingh.weathernow.network.IRequest;
import com.aniruddhsingh.weathernow.network.VolleyWeatherNowRequest;
import com.aniruddhsingh.weathernow.network.volley.VolleyErrorHelper;
import com.aniruddhsingh.weathernow.network.volley.VolleyRequestManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by taru on 11/15/2017.
 */
@ApplicationScope
@Module(includes = {AppModule.class})
public class NetworkModule {
    @Provides
    public IRequest getRequest(VolleyErrorHelper helper, Context context) {
        return new VolleyWeatherNowRequest(getVolleyErrorHelper(), getVolleyRequestManager(context));
    }

    @Provides
    public VolleyErrorHelper getVolleyErrorHelper() {
        return new VolleyErrorHelper();
    }

    @Provides
    public VolleyRequestManager getVolleyRequestManager(Context context) {
        return new VolleyRequestManager(context);
    }
}