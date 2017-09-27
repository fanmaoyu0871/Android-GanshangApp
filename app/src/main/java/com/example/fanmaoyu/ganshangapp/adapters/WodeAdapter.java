package com.example.fanmaoyu.ganshangapp.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fanmaoyu.ganshangapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanmaoyu on 2017/9/25.
 */

public class WodeAdapter extends BaseAdapter {

    private Context context;
    private String[] titles;
    private int[] images;

    public WodeAdapter(Context context) {
        this.context = context;
        this.titles = context.getResources().getStringArray(R.array.wode_titles);
        this.images = context.getResources().getIntArray(R.array.wode_images);

        TypedArray ar = context.getResources().obtainTypedArray(R.array.wode_images);
        int len = ar.length();
        images = new int[len];
        for (int i = 0; i < len; i++)
            images[i] = ar.getResourceId(i, 0);

        ar.recycle();
    }

    @Override
    public int getCount() {
        return this.titles.length;
    }

    @Override
    public Object getItem(int i) {
        return this.titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            view = View.inflate(this.context, R.layout.cell_wode, null);
            ButterKnife.bind(viewHolder, view);


            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.wode_left_imageview.setImageResource(this.images[i]);
        viewHolder.wode_title.setText(this.titles[i]);

        return view;
    }

    public class ViewHolder{
        @BindView(R.id.wode_left_imageview)
        ImageView wode_left_imageview;

        @BindView(R.id.wode_title)
        TextView wode_title;
    }
}
