package com.example.fanmaoyu.ganshangapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.fanmaoyu.ganshangapp.R;

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
            view = View.inflate(this.context, R.layout.cell_wode, null);
            ButterKnife.bind(this.context, view);

            viewHolder = new ViewHolder();

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    public class ViewHolder{

    }
}
