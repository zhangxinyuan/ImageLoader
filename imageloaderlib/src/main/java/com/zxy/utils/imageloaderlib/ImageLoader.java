package com.zxy.utils.imageloaderlib;

import android.content.Context;
import android.support.annotation.NonNull;

public class ImageLoader implements ILoaderStrategy {

    private volatile static ImageLoader mImageLoader;

    private ILoaderStrategy mStrategy;

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        if (mImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (mImageLoader == null) {
                    mImageLoader = new ImageLoader();
                }
            }
        }
        return mImageLoader;
    }

    public ImageLoader setImageLoaderStrategy(@NonNull ILoaderStrategy strategy) {
        this.mStrategy = strategy;
        return this;
    }

    @Override
    public void init(Context context) {
        if (mStrategy != null && context != null) {
            mStrategy.init(context);
        }
    }

    @Override
    public void loadImage(LoaderOptions options) {
        if (mStrategy != null) {
            mStrategy.loadImage(options);
        }
    }
}
