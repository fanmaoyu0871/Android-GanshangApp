package com.example.fanmaoyu.ganshangapp.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.example.fanmaoyu.ganshangapp.R;
import com.example.fanmaoyu.ganshangapp.activitys.CategoryActivity;
import com.example.fanmaoyu.ganshangapp.adapters.CategoryAdapter;
import com.example.fanmaoyu.ganshangapp.models.ShouyeModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanmaoyu on 2017/9/19.
 */

public class CategoryView extends FrameLayout {

    @BindView(R.id.category_gridview)
    GridView category_gridview;

    private  Context context;

    public CategoryView(@NonNull Context context) {
        super(context);
    }

    public CategoryView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = View.inflate(context, R.layout.view_category, this);

        ButterKnife.bind(this, view);


        this.context = context;
    }

    public CategoryView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void loadData(final ArrayList<ShouyeModel.CategoryModel> datas){
        CategoryAdapter adapter = new CategoryAdapter(this.context, datas);
        this.category_gridview.setAdapter(adapter);
        this.category_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Activity activity = (Activity) CategoryView.this.context;
                Intent intent = new Intent(activity, CategoryActivity.class);
                intent.putExtra("data", datas.get(i));
                activity.startActivity(intent);
            }
        });

    }
}
