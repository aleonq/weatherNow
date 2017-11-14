package com.aniruddhsingh.weathernow.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by taru on 11/14/2017.
 */

public class Util {

    public static String getDate(long seconds) {
        String dateFormat = "kk:mm:ss"; // 24 hour date format
        Log.d("funny", "time " + seconds);
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(seconds * 1000); // Converting time to millis
        return formatter.format(calendar.getTime());
    }

    public static String getLastSearchedCity(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_GENERIC, MODE_PRIVATE);
        // Reading city from SharedPreferences
        String value = prefs.getString(Constants.LAST_SEARCHED_CITY, null);
        return value;
    }

    public static void setLastSearchedCity(Context context, String city) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_GENERIC, MODE_PRIVATE);
        // Writing city to SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Constants.LAST_SEARCHED_CITY, city);
        editor.commit();
    }
}