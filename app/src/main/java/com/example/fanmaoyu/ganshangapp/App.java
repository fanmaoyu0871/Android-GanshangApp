package com.example.fanmaoyu.ganshangapp;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.example.fanmaoyu.ganshangapp.constant.UserinfoConstant;
import com.example.fanmaoyu.ganshangapp.models.UserModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


/**
 * Created by fanmaoyu on 2017/9/19.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(config);

        Logger.addLogAdapter(new AndroidLogAdapter());

        UserModel userModel = UserModel.getInstance();
        SharedPreferences sharedPreferences = getSharedPreferences(UserinfoConstant.PreferenceKey, Activity.MODE_PRIVATE);
        userModel.setNickName(sharedPreferences.getString(UserinfoConstant.Nickname, null));
        userModel.setHeadpic(sharedPreferences.getString(UserinfoConstant.UserImageUrl, null));
        userModel.setId(sharedPreferences.getInt(UserinfoConstant.UserId, 0));
    }
}
