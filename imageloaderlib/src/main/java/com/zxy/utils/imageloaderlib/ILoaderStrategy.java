package com.zxy.utils.imageloaderlib;

import android.content.Context;

public interface ILoaderStrategy {
    void init(Context context);

    void loadImage(LoaderOptions options);
}
