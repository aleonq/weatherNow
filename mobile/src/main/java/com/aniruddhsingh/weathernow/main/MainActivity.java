package com.aniruddhsingh.weathernow.main;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.aniruddhsingh.weathernow.App;
import com.aniruddhsingh.weathernow.R;
import com.aniruddhsingh.weathernow.beans.WeatherData;
import com.aniruddhsingh.weathernow.main.mvp.MainActivityMvp;
import com.aniruddhsingh.weathernow.network.volley.VolleyBitmapLruCache;
import com.aniruddhsingh.weathernow.util.Util;

import net.hockeyapp.android.CrashManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.android.volley.toolbox.ImageLoader.ImageCache;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityMvp.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainActivityMvp.Presenter presenter;

    private ImageLoader imageLoader;

    private View viewSnackbarRef;

    @BindView(R.id.tv_region)
    TextView tvRegion;

    @BindView(R.id.tv_desc)
    TextView tvDescription;

    @BindView(R.id.tv_temp_cur)
    TextView tvTemperature;

    @BindView(R.id.tv_humidity)
    TextView tvHumidity;

    @BindView(R.id.tv_temp_max)
    TextView tvTempMax;

    @BindView(R.id.tv_temp_min)
    TextView tvTempMin;

    @BindView(R.id.tv_pressure)
    TextView tvPressure;

    @BindView(R.id.tv_wind_speed)
    TextView tvWindSpeed;

    @BindView(R.id.tv_wind_deg)
    TextView tvWindDegree;

    @BindView(R.id.tv_sunrise)
    TextView tvSunrise;

    @BindView(R.id.tv_sunset)
    TextView tvSunset;

    @BindView(R.id.tv_visibility)
    TextView tvVisibility;

    @BindView(R.id.btn_search)
    Button btnSearchData;

    @BindView(R.id.niv_weather_icon)
    NetworkImageView nivWeatherIcon;
    @BindView(R.id.tv_search)
    TextView tvSearchCity;

    private Snackbar snackbar;

    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);
        presenter.setView(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewSnackbarRef = btnSearchData;
        ImageCache imageCache = new VolleyBitmapLruCache();
        imageLoader = new ImageLoader(Volley.newRequestQueue(this), imageCache);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        presenter.requestWeatherData(city);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.viewStopped();
    }

    @OnClick(R.id.btn_open_details)
    public void onClickedOpenDetails() {
        presenter.openDetailsButtonClicked();
    }

    @OnClick(R.id.btn_search)
    public void onClickedSearch() {
        String city = tvSearchCity.getText().toString();
        presenter.requestWeatherData(city);
    }

    @Override
    public void openDetailsExternally(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void loadWeatherIcon(String url) {
        nivWeatherIcon.setImageUrl(url, imageLoader);
        nivWeatherIcon.setErrorImageResId(R.mipmap.ic_launcher_bg);
    }

    @Override
    public void displayDefaultIcon() {
        nivWeatherIcon.setDefaultImageResId(R.mipmap.ic_launcher_round);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void displayData(WeatherData data) {
        // Setting up Brief Data
        String title = String.format(getString(R.string.text_titile), data.getName(), data.getSys().getCountry());
        tvRegion.setText(title);
        String description = null;
        try {
            description = data.getWeather().get(0).getDescription();
            if (description.length() > 1) {
                description = description.substring(0, 1).toUpperCase() + description.substring(1).toLowerCase();
            }
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "Description is available. " + e.getMessage());
        }
        tvDescription.setText(description);
        String temprature = String.format(getString(R.string.text_temp), data.getMain().getTemp());
        tvTemperature.setText(temprature);
        String humidity = String.format(getString(R.string.text_humidity), data.getMain().getHumidity());
        tvHumidity.setText(humidity);

        // Setting up Detailed data
        String maxTemp = String.format(getString(R.string.text_temp_max), data.getMain().getTempMax());
        tvTempMax.setText(maxTemp);
        String minTemp = String.format(getString(R.string.text_temp_min), data.getMain().getTempMin());
        tvTempMin.setText(minTemp);
        String pressure = String.format(getString(R.string.text_pressure), data.getMain().getPressure());
        tvPressure.setText(pressure);
        String windSpeed = String.format(getString(R.string.text_wind_speed), data.getWind().getSpeed());
        tvWindSpeed.setText(windSpeed);
        String windDir = String.format(getString(R.string.text_wind_dir), data.getWind().getDeg());
        tvWindDegree.setText(windDir);
        String sunrise = String.format(getString(R.string.text_sunrise), Util.getDate(data.getSys().getSunrise()));
        tvSunrise.setText(sunrise);
        String sunset = String.format(getString(R.string.text_sunset), Util.getDate(data.getSys().getSunset()));
        tvSunset.setText(sunset);
        String visibility = String.format(getString(R.string.text_visibility), data.getVisibility());
        if (visibility != null) {
            tvVisibility.setText(visibility);
        } else {
            tvVisibility.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showError(String error) {
        Snackbar.make(viewSnackbarRef, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        snackbar = Snackbar
                .make(viewSnackbarRef, getString(R.string.progress_message), Snackbar.LENGTH_INDEFINITE);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    @Override
    public void hideProgress() {
        if (snackbar != null && snackbar.isShownOrQueued()) {
            snackbar.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForCrashes();
    }

    private void checkForCrashes() {
        CrashManager.register(this);
    }
}