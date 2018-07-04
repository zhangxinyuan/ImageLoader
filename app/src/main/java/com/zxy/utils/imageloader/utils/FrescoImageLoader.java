package com.zxy.utils.imageloader.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.zxy.utils.imageloaderlib.ILoaderStrategy;
import com.zxy.utils.imageloaderlib.LoaderOptions;

public class FrescoImageLoader implements ILoaderStrategy {

    @Override
    public void init(Context context) {
        Fresco.initialize(context);
    }

    @Override
    public void loadImage(LoaderOptions options) {
        View view = options.getViewContainer();
        if (view instanceof SimpleDraweeView) {
            SimpleDraweeView draweeView = (SimpleDraweeView) view;
            GenericDraweeHierarchyBuilder hierarchyBuilder = GenericDraweeHierarchyBuilder.newInstance(view.getContext()
                    .getResources());

            Uri imageUri = null;
            //网络图片处理
            if (URLUtil.isHttpUrl(options.getUrl()) || URLUtil.isHttpsUrl(options.getUrl())) {
                imageUri = Uri.parse(options.getUrl());
            }
            //res资源处理
            if (options.getResource() != -1) {
                imageUri = Uri.parse("res:///" + options.getResource());
            }
            // 本地路径图片处理
            if (!TextUtils.isEmpty(options.getUrl()) && !options.getUrl().contains("http")) {
                imageUri = Uri.parse("file:///" + options.getUrl());
            }
            if (imageUri == null) {
                return;
            }

            if (options.getPlaceholderDrawable() != -1) {
                hierarchyBuilder.setPlaceholderImage(options.getPlaceholderDrawable(), ScalingUtils.ScaleType.CENTER_CROP);
            }
            if (options.getErrorDrawable() != -1) {
                hierarchyBuilder.setFailureImage(options.getErrorDrawable(), ScalingUtils.ScaleType.CENTER_CROP);
            }

            //圆形处理
            RoundingParams roundingParams = null;
            if (options.asCircle()) {
                roundingParams = new RoundingParams();
                roundingParams.setRoundAsCircle(true);
            }
            //四个圆角处理
            if (options.getCornersRadii() != null) {
                if (roundingParams == null) {
                    roundingParams = new RoundingParams();
                }
                roundingParams.setCornersRadii(options.getCornersRadii()[0], options.getCornersRadii()[1],
                        options.getCornersRadii()[2], options.getCornersRadii()[3]);
            }
            if (options.getCornersRadius() != -1) {
                if (roundingParams == null) {
                    roundingParams = new RoundingParams();
                }
                roundingParams.setCornersRadius(options.getCornersRadius());
                roundingParams.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
            }
            if (roundingParams != null) {
                hierarchyBuilder.setRoundingParams(roundingParams);
            }

            hierarchyBuilder.setOverlay(options.getOverlayDrawable());

            GenericDraweeHierarchy hierarchy = hierarchyBuilder.build();

            hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);

            draweeView.setVisibility(View.VISIBLE);
            draweeView.setHierarchy(hierarchy);

            PipelineDraweeControllerBuilder controllerBuilder = Fresco.newDraweeControllerBuilder().setUri(imageUri)
                    .setAutoPlayAnimations(options.isAsGif());

            ImageRequestBuilder imageRequestBuilder = ImageRequestBuilder.newBuilderWithSource(imageUri);
            if (options.getImageSize() != null) {
                imageRequestBuilder.setResizeOptions(new ResizeOptions(options.getImageSize().getWidth(), options
                        .getImageSize().getWidth()));
            }
            if (!options.isAsGif()) {
                imageRequestBuilder.setImageDecodeOptions(ImageDecodeOptions.newBuilder().setForceStaticImage(true)
                        .build());
            }
            ImageRequest request = imageRequestBuilder.build();
            controllerBuilder.setImageRequest(request);
            DraweeController controller = controllerBuilder.build();

            draweeView.setController(controller);
        }
    }
}
