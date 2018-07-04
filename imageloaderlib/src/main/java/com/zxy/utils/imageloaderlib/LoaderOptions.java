package com.zxy.utils.imageloaderlib;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;

public class LoaderOptions {
    private View viewContainer;  // 图片容器
    private String url;  // 图片地址
    private Integer resource;  // 图片地址
    private int placeholderDrawable;  // 设置展位图
    private Drawable overlayDrawable;
    private ImageSize imageSize;  //设置图片的大小
    private int errorDrawable;  //是否展示加载错误的图片
    private boolean asGif = false;   //是否作为gif展示
    private boolean isCrossFade = true; //是否渐变平滑的显示图片
    private boolean isSkipMemoryCache = false; //是否跳过内存缓存
    private boolean asCircle = false; // 是否是圆圈
    private float cornersRadius = -1; //圆角设置
    private float[] cornersRadii = null; //四个圆角设置

    public LoaderOptions(Builder builder) {
        this.viewContainer = builder.mViewContainer;
        this.url = builder.url;
        this.resource = builder.resource;
        this.placeholderDrawable = builder.placeholderDrawable;
        this.overlayDrawable = builder.overlayDrawable;
        this.imageSize = builder.mImageSize;
        this.asGif = builder.asGif;
        this.errorDrawable = builder.errorDrawable;
        this.isCrossFade = builder.isCrossFade;
        this.isSkipMemoryCache = builder.isSkipMemoryCache;
        this.asCircle = builder.asCircle;
        this.cornersRadius = builder.cornersRadius;
        this.cornersRadii = builder.cornersRadii;
    }

    public View getViewContainer() {
        return viewContainer;
    }

    public void setViewContainer(View viewContainer) {
        this.viewContainer = viewContainer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

    public int getPlaceholderDrawable() {
        return placeholderDrawable;
    }

    public void setPlaceholderDrawable(int placeholderDrawable) {
        this.placeholderDrawable = placeholderDrawable;
    }

    public Drawable getOverlayDrawable() {
        return overlayDrawable;
    }

    public void setOverlayDrawable(Drawable overlayDrawable) {
        this.overlayDrawable = overlayDrawable;
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public void setImageSize(ImageSize imageSize) {
        this.imageSize = imageSize;
    }

    public int getErrorDrawable() {
        return errorDrawable;
    }

    public void setErrorDrawable(int errorDrawable) {
        this.errorDrawable = errorDrawable;
    }

    public boolean isAsGif() {
        return asGif;
    }

    public void setAsGif(boolean asGif) {
        this.asGif = asGif;
    }

    public boolean isCrossFade() {
        return isCrossFade;
    }

    public void setCrossFade(boolean crossFade) {
        isCrossFade = crossFade;
    }

    public boolean isSkipMemoryCache() {
        return isSkipMemoryCache;
    }

    public void setSkipMemoryCache(boolean skipMemoryCache) {
        isSkipMemoryCache = skipMemoryCache;
    }

    public boolean asCircle() {
        return asCircle;
    }

    public float getCornersRadius() {
        return cornersRadius;
    }

    public float[] getCornersRadii() {
        return cornersRadii;
    }

    public final static class Builder {
        private View mViewContainer;
        private String url;
        private Integer resource = -1;
        private ImageSize mImageSize;
        private int errorDrawable = -1;
        private int placeholderDrawable = -1;
        private Drawable overlayDrawable;
        private boolean asGif = false;
        private boolean isCrossFade = false;
        private boolean isSkipMemoryCache = false;
        private boolean asCircle = false;
        private float cornersRadius = -1;
        private float[] cornersRadii = null;

        public Builder(@NonNull View v, @NonNull String url) {
            this.url = url;
            this.mViewContainer = v;
        }

        public Builder(@NonNull View v, @NonNull Integer resource) {
            this.resource = resource;
            this.mViewContainer = v;
        }

        public Builder placeholder(@DrawableRes int placeholderDrawable) {
            this.placeholderDrawable = placeholderDrawable;
            return this;
        }

        public Builder overlay(Drawable overlayDrawable) {
            this.overlayDrawable = overlayDrawable;
            return this;
        }

        public Builder isCrossFade(boolean isCrossFade) {
            this.isCrossFade = isCrossFade;
            return this;
        }

        public Builder isSkipMemoryCache(boolean isSkipMemoryCache) {
            this.isSkipMemoryCache = isSkipMemoryCache;
            return this;
        }

        public Builder size(int width, int height) {
            this.mImageSize = new ImageSize(width, height);
            return this;
        }

        public Builder asGif(boolean asGif) {
            this.asGif = asGif;
            return this;
        }

        public Builder error(@DrawableRes int errorDrawable) {
            this.errorDrawable = errorDrawable;
            return this;
        }

        public Builder asCircle(boolean asCircle) {
            this.asCircle = asCircle;
            return this;
        }

        public Builder cornersRadius(float cornersRadius) {
            this.cornersRadius = cornersRadius;
            return this;
        }

        public Builder cornersRadii(float[] cornersRadii) {
            this.cornersRadii = cornersRadii;
            return this;
        }

        public LoaderOptions build() {
            return new LoaderOptions(this);
        }
    }

    public final static class ImageSize {
        private int width = 0;
        private int height = 0;

        ImageSize(int width, int heigh) {
            this.width = width;
            this.height = heigh;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }
}
