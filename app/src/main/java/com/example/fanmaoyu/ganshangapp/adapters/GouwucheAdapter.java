package com.example.fanmaoyu.ganshangapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fanmaoyu.ganshangapp.R;
import com.example.fanmaoyu.ganshangapp.constant.ServiceConstant;
import com.example.fanmaoyu.ganshangapp.models.GouwucheModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanmaoyu on 2017/9/28.
 */

public class GouwucheAdapter extends BaseAdapter {

    private Context context;
    public ArrayList<GouwucheModel> datas;

    public GouwucheAdapter(Context context, ArrayList<GouwucheModel> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return this.datas.size();
    }

    @Override
    public Object getItem(int i) {
        return this.datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            view = View.inflate(this.context, R.layout.cell_shouye, null);
            ButterKnife.bind(viewHolder, view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        GouwucheModel product = this.datas.get(i);
        ImageLoader.getInstance().displayImage(ServiceConstant.ServiceImagePath+product.getProduction().getImage(), viewHolder.shouyeImageview);
        viewHolder.shouyeName.setText(product.getProduction().getName());
        viewHolder.shouyeIntro.setText(product.getProduction().getIdeas());
        viewHolder.shouyePrice.setText(product.getProduction().getMinimumPrice() + "");
        viewHolder.shouyeYishouchu.setText(product.getProduction().getClinchCount() + "");
        viewHolder.shouyeYishoucang.setText(product.getProduction().getCollectCount() + "");
        viewHolder.gouwuche_check_imageview.setVisibility(View.VISIBLE);

        return view;

    }

    public class ViewHolder{

        @BindView(R.id.gouwuche_check_imageview)
        ImageView gouwuche_check_imageview;

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
