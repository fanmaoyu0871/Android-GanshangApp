package com.example.fanmaoyu.ganshangapp.tools;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by fanmaoyu on 2017/9/19.
 */

public class DisplayUtils {
    public static int getScreenWidth(Activity context){
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.widthPixels;
    }

    public static int getScreenHeight(Activity context){
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.heightPixels;
    }
}
