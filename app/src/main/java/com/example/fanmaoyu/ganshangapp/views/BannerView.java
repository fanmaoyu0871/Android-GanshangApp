package com.example.fanmaoyu.ganshangapp.views;


import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.fanmaoyu.ganshangapp.R;
import com.example.fanmaoyu.ganshangapp.adapters.BannerAdapter;
import com.example.fanmaoyu.ganshangapp.tools.DisplayUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by fanmaoyu on 2017/9/19.
 */

public class BannerView extends RelativeLayout {

    @BindView(R.id.banner)
    ViewPager viewPager;

    @BindView(R.id.banner_container)
    RelativeLayout banner_container;

    @BindView(R.id.indicator)
    CircleIndicator indicator;

    private Context context;

    public BannerView(Context context) {
        super(context);

    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.view_banner, this);
        ButterKnife.bind(this, view);

        int width = DisplayUtils.getScreenWidth((Activity) context);
        int bannerHeight = (int) (300.0f * width / 630.0f);
        LayoutParams layoutParams = new LayoutParams(width, bannerHeight);
        banner_container.setLayoutParams(layoutParams);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //加载数据
    public void loadData(ArrayList<String> urls){

        BannerAdapter bannerAdapter = new BannerAdapter(this.context, urls);
        this.viewPager.setAdapter(bannerAdapter);
        indicator.setViewPager(this.viewPager);
    }

}
