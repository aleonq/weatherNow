package com.aniruddhsingh.weathernow.network.volley;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by taru on 11/14/2017.
 */

public class VolleyBitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {
    public VolleyBitmapLruCache() {
        this(getDefaultLruCacheSize());
    }

    public VolleyBitmapLruCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

    public static int getDefaultLruCacheSize() {
        final int maxMemory =
                (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        return cacheSize;
    }
}