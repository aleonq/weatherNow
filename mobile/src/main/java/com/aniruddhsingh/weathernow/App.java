package com.aniruddhsingh.weathernow;

/**
 * Created by taru on 11/14/2017.
 */

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.aniruddhsingh.weathernow.injection.AppModule;
//import com.aniruddhsingh.weathernow.network.LruBitmapCache;

public class App extends Application {

    public static final String TAG = App.class
            .getSimpleName();

    private AppComponent daggerAppComponent;



//    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        daggerAppComponent = DaggerAppComponent.builder().appModule(new AppModule(getApplicationContext())).build();
//        mInstance = this;
    }

    public AppComponent getAppComponent() {
        return daggerAppComponent;
    }
//
//    public static synchronized App getInstance() {
//        return mInstance;
//    }

}