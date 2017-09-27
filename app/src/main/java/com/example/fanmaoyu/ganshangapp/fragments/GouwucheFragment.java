package com.example.fanmaoyu.ganshangapp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fanmaoyu.ganshangapp.R;
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
 * Created by fanmaoyu on 2017/9/18.
 */

public class GouwucheFragment extends Fragment {

    @BindView(R.id.nav_center_textview)
    TextView nav_center_textview;

    @BindView(R.id.nav_left_imageview)
    ImageView nav_left_imageview;

    @BindView(R.id.gouwuche_listview)
    ListView gouwuche_listview;

    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_gouwuche, container, false);
        ButterKnife.bind(this, view);

        nav_left_imageview.setVisibility(View.INVISIBLE);
        nav_center_textview.setText("购物车");

        //加载数据
//        loadData();

        return view;
    }

    private void loadData(){
        //handler 处理
        this.handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0x01:
                        ArrayList<ShouyeModel.Product> product_list = msg.getData().getParcelableArrayList("category_list");

                        break;
                }
            }
        };

        //加载数据
        Networking networking = new Networking(this.getActivity(), new Networking.NetResponseInterface() {
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
                    bundle.putParcelableArrayList("gouwuche_list", product_list);
                    msg.setData(bundle);
                    GouwucheFragment.this.handler.sendMessage(msg);
                }
            }

            @Override
            public void failCallback(String errormsg) {

            }
        });


        HashMap<String, String> params = new HashMap<String, String>();
        networking.startReq(params, "categoryReq");
    }
}
