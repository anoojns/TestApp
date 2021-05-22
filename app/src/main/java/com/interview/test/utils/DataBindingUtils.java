package com.interview.test.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class DataBindingUtils {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        ImageLoader.getInstance().displayImage(imageView, url);
    }

    @BindingAdapter({"contentText","searchText"})
    public static void setFilterText(TextView tv, String contentText, String searchText) {

        if (!TextUtils.isEmpty(searchText) &&
                contentText.toLowerCase().contains(searchText.toLowerCase()) && searchText.length() > 2) {

            int startPosition = contentText.toLowerCase().indexOf(searchText.toLowerCase());
            int endPosition = startPosition + searchText.length();

            if (startPosition != -1){
                Spannable spanText = Spannable.Factory.getInstance().newSpannable(contentText);
                spanText.setSpan(new ForegroundColorSpan(Color.YELLOW), startPosition, endPosition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                tv.setText(spanText, TextView.BufferType.SPANNABLE);
            } else {
                tv.setText(contentText);
            }


        } else {
            tv.setText(contentText);
        }
    }
    
}
