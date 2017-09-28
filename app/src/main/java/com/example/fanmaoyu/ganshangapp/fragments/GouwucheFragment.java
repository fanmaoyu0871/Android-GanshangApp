package com.example.fanmaoyu.ganshangapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fanmaoyu.ganshangapp.R;
import com.example.fanmaoyu.ganshangapp.adapters.GouwucheAdapter;
import com.example.fanmaoyu.ganshangapp.events.NetworkEvent;
import com.example.fanmaoyu.ganshangapp.events.PageEvent;
import com.example.fanmaoyu.ganshangapp.models.GouwucheModel;
import com.example.fanmaoyu.ganshangapp.models.UserModel;
import com.example.fanmaoyu.ganshangapp.network.Networking;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    @BindView(R.id.empty_container)
    LinearLayout empty_container;

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NetworkEvent.GouwucheReqEvent event){
        String code = event.getCode();
        if(code.equals("Succ")){
            JsonElement jsonElement = event.getData();
            if(jsonElement != null && jsonElement.isJsonObject()){

                JsonArray jsonArray =  jsonElement.getAsJsonObject().getAsJsonArray("shopCart");
                ArrayList<GouwucheModel> gouwuche_list = new ArrayList<GouwucheModel>();

                for(JsonElement product : jsonArray){
                    Gson gson = new Gson();
                    GouwucheModel gouwucheModel = gson.fromJson(product, GouwucheModel.class);
                    gouwuche_list.add(gouwucheModel);
                }

                GouwucheAdapter adapter = new GouwucheAdapter(GouwucheFragment.this.getActivity(), gouwuche_list);
                this.gouwuche_listview.setAdapter(adapter);
            }
        }else{

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onRefreshEvent(PageEvent.GouwucheEvent event){

        UserModel userModel = UserModel.getInstance();
        if(userModel.getId() != 0){ //有user_id 就刷新
            this.empty_container.setVisibility(View.INVISIBLE);
            this.gouwuche_listview.setVisibility(View.VISIBLE);

            //加载数据
            this.loadData(userModel.getId() + "");
        }else{
            this.empty_container.setVisibility(View.VISIBLE);
            this.gouwuche_listview.setVisibility(View.INVISIBLE);
        }

        //移除黏性事件
        PageEvent.GouwucheEvent gouwucheEvent = EventBus.getDefault().getStickyEvent(PageEvent.GouwucheEvent.class);
        if(gouwucheEvent != null){
            EventBus.getDefault().removeStickyEvent(gouwucheEvent);
        }
    }


    private void loadData(String userId){

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        Networking.getInstance(this.getActivity()).startReq(params, "gouwucheReq");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_gouwuche, container, false);
        ButterKnife.bind(this, view);

        nav_left_imageview.setVisibility(View.INVISIBLE);
        nav_center_textview.setText("购物车");

        this.gouwuche_listview.setVisibility(View.INVISIBLE);
        this.empty_container.setVisibility(View.INVISIBLE);

        return view;
    }

}
