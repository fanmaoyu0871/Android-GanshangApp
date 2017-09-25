package com.example.fanmaoyu.ganshangapp.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fanmaoyu.ganshangapp.R;
import com.example.fanmaoyu.ganshangapp.constant.ServiceConstant;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanmaoyu on 2017/9/19.
 */

public class BannerAdapter extends PagerAdapter {

    private Context context;
    private List urls;

    @BindView(R.id.banner_imageView)
    ImageView banner_imageView;

    public BannerAdapter(Context context, List urls) {
        this.context = context;
        this.urls = urls;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = View.inflate(this.context, R.layout.cell_banner, null);
        container.addView(view);

        ButterKnife.bind(this, view);
        ImageLoader.getInstance().displayImage(ServiceConstant.ServiceImagePath+this.urls.get(position), banner_imageView);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return this.urls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
