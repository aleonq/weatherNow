package com.aniruddhsingh.weathernow.network;

import android.content.Context;

import com.aniruddhsingh.weathernow.main.mvp.MainActivityMvp;

/**
 * Created by taru on 11/14/2017.
 */

public interface IRequest {
    void setModel(MainActivityMvp.Model model);

    void sendRequest(Context context, String city);
}
