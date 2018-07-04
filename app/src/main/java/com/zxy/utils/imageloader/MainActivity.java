package com.zxy.utils.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zxy.utils.imageloaderlib.ImageLoader;
import com.zxy.utils.imageloaderlib.LoaderOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = findViewById(R.id.image_demo);
        String url = "https://images.ifanr.cn/wp-content/uploads/2018/07/201802211441511ersooqdazr4653.jpg!720";
        LoaderOptions options = new LoaderOptions.Builder(image, url).build();
        ImageLoader.getInstance().loadImage(options);
    }
}
