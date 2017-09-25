package com.example.fanmaoyu.ganshangapp.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ShouyeModel.CategoryModel> datas;

    public CategoryAdapter(Context context, ArrayList<ShouyeModel.CategoryModel> datas) {
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
        if(view == null){
            viewHolder = new ViewHolder();
            view = View.inflate(this.context, R.layout.cell_category, null);
            ButterKnife.bind(viewHolder, view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        //计算gridview cell大小
        GridView gridView = (GridView) viewGroup;
        int hor_spacing = gridView.getHorizontalSpacing();
        int column_num = gridView.getNumColumns();
        int item_width = (gridView.getWidth() - hor_spacing*(column_num+1)) / 4 - 80;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(item_width, item_width);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        viewHolder.category_imageview.setLayoutParams(layoutParams);


        ShouyeModel.CategoryModel categoryModel = this.datas.get(i);
        ImageLoader.getInstance().displayImage(ServiceConstant.ServiceImagePath+categoryModel.getImage(), viewHolder.category_imageview);
        viewHolder.category_title.setText(categoryModel.getName());


        return view;
    }

    public class ViewHolder{


        @BindView(R.id.category_imageview)
        ImageView category_imageview;

        @BindView(R.id.category_title)
        TextView category_title;
    }
}
