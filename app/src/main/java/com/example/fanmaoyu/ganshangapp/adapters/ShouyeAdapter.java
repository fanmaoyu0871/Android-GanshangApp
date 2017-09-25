package com.example.fanmaoyu.ganshangapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fanmaoyu.ganshangapp.R;
import com.example.fanmaoyu.ganshangapp.constant.ServiceConstant;
import com.example.fanmaoyu.ganshangapp.models.ShouyeModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanmaoyu on 2017/9/19.
 */

public class ShouyeAdapter extends BaseAdapter {

    private ArrayList<ShouyeModel.Product> dataArray;
    private Context context;

    public ShouyeAdapter(Context context, ArrayList<ShouyeModel.Product> datas) {
        this.context = context;
        this.dataArray = datas;
    }

    @Override
    public int getCount() {
        return this.dataArray.size();
    }

    @Override
    public Object getItem(int i) {
        return this.dataArray.get(i);
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
            view = View.inflate(this.context, R.layout.cell_shouye, null);
            ButterKnife.bind(viewHolder, view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        ShouyeModel.Product product = this.dataArray.get(i);
        ImageLoader.getInstance().displayImage(ServiceConstant.ServiceImagePath+product.getImage(), viewHolder.shouyeImageview);
        viewHolder.shouyeName.setText(product.getName());
        viewHolder.shouyeIntro.setText(product.getIdeas());
        viewHolder.shouyePrice.setText(product.getMinimumPrice() + "");
        viewHolder.shouyeYishouchu.setText(product.getClinchCount() + "");
        viewHolder.shouyeYishoucang.setText(product.getCollectCount() + "");

        return view;
    }

    public class ViewHolder{
        @BindView(R.id.shouye_imageview)
        ImageView shouyeImageview;

        @BindView(R.id.shouye_name)
        TextView shouyeName;

        @BindView(R.id.shouye_intro)
        TextView shouyeIntro;


        @BindView(R.id.shouye_price)
        TextView shouyePrice;


        @BindView(R.id.shouye_yishouchu)
        TextView shouyeYishouchu;


        @BindView(R.id.shouye_yishoucang)
        TextView shouyeYishoucang;

    }
}
