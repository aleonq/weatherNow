package com.aniruddhsingh.weathernow.util;

/**
 * Created by taru on 11/14/2017.
 */

public class Constants {
    /*
    *Open Weather Map API Key
    * */
    /*
    * ToDo:
    * 1. Move online to immediately make hte app adapt to any changes
    * in the billing plans or the server key for any reason
    *
    * OR
    *
    * 2. Move to C/C++ file to make obfuscation stronger. Retrival by NDK at runtime.
    * */
    public static final String API_KEY = "d6c7fc39376bc5607a496e7bc3fb5d3c";

    /*
    * Timeout in ms
    * */
    public static final int VOLLEY_TIMEOUT_MS = 2500;

    /* Base Url */
    public static final String URL_BASE_WEATHER_ICON = "http://openweathermap.org/";

    /* Icon url */
    public static final String URL_WEATHER_ICON = URL_BASE_WEATHER_ICON + "img/w/";

    /* Icon type */
    public static final String ICON_TYPE = ".png";

    /* Data url */
    public static final String URL_BASE_WEATHER = "http://api.openweathermap.org/data/2.5/weather?";

    /*Url to get the weather details*/
    public static final String URL_WEATHER_NOW = URL_BASE_WEATHER + "q=%s&appid=" + API_KEY;

    /*Url to open webpage for a city in external browser*/
    public static final String URL_WEATHER_HTML = URL_BASE_WEATHER + "q=%s&mode=html&appid=" + API_KEY;

    /*Preference file name to store generic information*/
    public static final String PREFS_GENERIC = "generic_prefs";

    /*Last searched city key*/
    public static final String LAST_SEARCHED_CITY = "last_searched_city";
}