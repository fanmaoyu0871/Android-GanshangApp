package com.example.fanmaoyu.ganshangapp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fanmaoyu.ganshangapp.R;
import com.example.fanmaoyu.ganshangapp.adapters.ShouyeAdapter;
import com.example.fanmaoyu.ganshangapp.events.NetworkEvent;
import com.example.fanmaoyu.ganshangapp.models.ShouyeModel;
import com.example.fanmaoyu.ganshangapp.network.Networking;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanmaoyu on 2017/9/25.
 */

public class CategoryActivity extends AppCompatActivity {
    @BindView(R.id.nav_center_textview)
    TextView nav_title;

    @BindView(R.id.category_listview)
    ListView category_listview;

    @BindView(R.id.nav_left_imageview)
    ImageView nav_left_imageview;


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        ShouyeModel.CategoryModel categoryModel = intent.getParcelableExtra("data");
        this.nav_title.setText(categoryModel.getName());
        this.nav_left_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryActivity.this.finish();
            }
        });

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("attributeId", categoryModel.getId());
        Networking.getInstance(this).startReq(params, "categoryReq");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NetworkEvent event){
        String code = event.getCode();

        if(code.equals("Succ")){
            JsonElement jsonElement = event.getData();

            if(jsonElement != null && jsonElement.isJsonArray()){

                JsonArray category_array = jsonElement.getAsJsonArray();

                //listview data
                ArrayList<ShouyeModel.Product> product_list = new ArrayList<ShouyeModel.Product>();
                Gson gson = new Gson();
                ArrayList<ShouyeModel.Product> tmp_list = gson.fromJson(category_array, new TypeToken<ArrayList<ShouyeModel.Product>>(){}.getType());
                product_list.addAll(tmp_list);

                CategoryActivity.this.category_listview.setAdapter(
                        new ShouyeAdapter(CategoryActivity.this, product_list));
            }
        }
    }

}
