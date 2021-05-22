package com.interview.test.utils;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.interview.test.R;
import com.interview.test.TestingApp;

public class ImageLoader {

    private static ImageLoader instance;

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    public void displayImage(ImageView imageView, String url) {
        if (url.startsWith("http")) {
            Glide.with(TestingApp.getInstance()).load(url).placeholder(R.drawable.placeholder_for_missing_posters).into(imageView);
        } else{
            Glide.with(TestingApp.getInstance()).load(getImageFromName(url)).placeholder(R.drawable.placeholder_for_missing_posters).into(imageView);
        }
    }


    private int getImageFromName (String name) {
        if (name.indexOf(".") > 0) {
            name = name.substring(0, name.lastIndexOf("."));
        }
        return  TestingApp.getInstance().getResources().getIdentifier(name, "drawable", TestingApp.getInstance().getPackageName());
    }

}
