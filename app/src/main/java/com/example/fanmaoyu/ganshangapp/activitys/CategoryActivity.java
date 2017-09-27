package com.example.fanmaoyu.ganshangapp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fanmaoyu.ganshangapp.R;
import com.example.fanmaoyu.ganshangapp.adapters.ShouyeAdapter;
import com.example.fanmaoyu.ganshangapp.models.ShouyeModel;
import com.example.fanmaoyu.ganshangapp.network.Networking;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

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

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        this.loaddata();
    }



    private void loaddata(){
        //handler 处理
        this.handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0x01:
                        ArrayList<ShouyeModel.Product> product_list = msg.getData().getParcelableArrayList("category_list");
                        CategoryActivity.this.category_listview.setAdapter(
                                new ShouyeAdapter(CategoryActivity.this, product_list));
                        break;
                }
            }
        };

        //加载数据
        Networking networking = new Networking(this, new Networking.NetResponseInterface() {
            @Override
            public void successCallback(JsonElement jsonElement) {

                if(jsonElement != null && jsonElement.isJsonArray()){

                    JsonArray category_array = jsonElement.getAsJsonArray();

                    //listview data
                    ArrayList<ShouyeModel.Product> product_list = new ArrayList<ShouyeModel.Product>();
                    Gson gson = new Gson();
                    ArrayList<ShouyeModel.Product> tmp_list = gson.fromJson(category_array, new TypeToken<ArrayList<ShouyeModel.Product>>(){}.getType());
                    product_list.addAll(tmp_list);

                    Message msg = new Message();
                    msg.what = 0x01;
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("category_list", product_list);
                    msg.setData(bundle);
                    CategoryActivity.this.handler.sendMessage(msg);
                }
            }

            @Override
            public void failCallback(String errormsg) {

            }
        });

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
        networking.startReq(params, "categoryReq");
    }
}
