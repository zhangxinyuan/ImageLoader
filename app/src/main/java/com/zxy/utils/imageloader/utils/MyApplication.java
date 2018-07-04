package com.zxy.utils.imageloader.utils;

import android.app.Application;

import com.zxy.utils.imageloaderlib.ImageLoader;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.getInstance().setImageLoaderStrategy(new FrescoImageLoader()).init(this);
    }
}
