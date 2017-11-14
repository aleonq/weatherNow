package com.aniruddhsingh.weathernow.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.aniruddhsingh.weathernow.App;
import com.aniruddhsingh.weathernow.beans.WeatherData;
import com.aniruddhsingh.weathernow.main.mvp.MainActivityMvp;
import com.aniruddhsingh.weathernow.network.volley.VolleyErrorHelper;
import com.aniruddhsingh.weathernow.network.volley.VolleyRequestManager;
import com.aniruddhsingh.weathernow.util.Constants;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by taru on 11/14/2017.
 */

public class VolleyWeatherNowRequest implements IRequest {
    private static final String TAG = VolleyWeatherNowRequest.class.getSimpleName();

    private String tag_json_obj = "json_obj_req";
    private MainActivityMvp.Model model;
    private VolleyErrorHelper volleyErrorHelper;
    private VolleyRequestManager requestManager;
    private String url;

    public VolleyWeatherNowRequest(VolleyErrorHelper volleyErrorHelper, VolleyRequestManager requestManager) {
        this.volleyErrorHelper = volleyErrorHelper;
        this.requestManager = requestManager;
    }

    @Override
    public void setModel(MainActivityMvp.Model model) {
        this.model = model;
    }

    @Override
    public void sendRequest(final Context context, final String city) {
        url = String.format(Constants.URL_WEATHER_NOW, city);
        Log.d(TAG, "weather url: " + url);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        WeatherData data = gson.fromJson(response.toString(), WeatherData.class);
                        model.setData(data, city);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                model.setError(volleyErrorHelper.getMessage(error, context));
                // hide the progress dialog
            }
        });
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                Constants.VOLLEY_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to request queue
        requestManager.addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}