package com.example.fanmaoyu.ganshangapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fanmaoyu.ganshangapp.R;
import com.example.fanmaoyu.ganshangapp.adapters.ShouyeAdapter;
import com.example.fanmaoyu.ganshangapp.events.NetworkEvent;
import com.example.fanmaoyu.ganshangapp.models.ShouyeModel;
import com.example.fanmaoyu.ganshangapp.network.Networking;
import com.example.fanmaoyu.ganshangapp.views.BannerView;
import com.example.fanmaoyu.ganshangapp.views.CategoryView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanmaoyu on 2017/9/18.
 */

public class ShouyeFragment extends Fragment {

    @BindView(R.id.nav_center_textview)
    TextView nav_center_textview;

    @BindView(R.id.nav_left_imageview)
    ImageView nav_left_imageview;

    @BindView(R.id.nav_right_imageview)
    ImageView nav_right_imageview;

    @BindView(R.id.shouye_listview)
    ListView shouye_listview;

    private BannerView bannerView;
    private CategoryView categoryView;

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
    public void onMessageEvent(NetworkEvent.ShouyeReqEvent event){
        String code = event.getCode();

        if(code.equals("Succ")){
            JsonElement jsonElement = event.getData();

            if(jsonElement.isJsonArray() || jsonElement.isJsonNull())
                return;

            JsonObject response = jsonElement.getAsJsonObject();

            JsonArray subjects = response.getAsJsonArray("subjects");
            //banner data
            ArrayList<String> imageUrls = new ArrayList<String>();
            for (JsonElement element : subjects){

                JsonObject subject = element.getAsJsonObject();
                String image_url = subject.get("image").getAsString();
                imageUrls.add(image_url);
            }

            //categoryview data
            JsonElement attributes = response.get("attribute");
            Gson gson = new Gson();
            ArrayList<ShouyeModel.CategoryModel> category_list =
                    gson.fromJson(attributes, new TypeToken<ArrayList<ShouyeModel.CategoryModel>>(){}.getType());


            //listview data
            JsonObject products = response.getAsJsonObject("products");
            ArrayList<ShouyeModel.Product> product_list = new ArrayList<ShouyeModel.Product>();
            for (Map.Entry<String, JsonElement> entry: products.entrySet()){
                JsonElement category_array = entry.getValue();

                ArrayList<ShouyeModel.Product> tmp_list = gson.fromJson(category_array, new TypeToken<ArrayList<ShouyeModel.Product>>(){}.getType());
                product_list.addAll(tmp_list);
            }

            //刷新banner
            bannerView.loadData(imageUrls);

            //刷新分类视图
            categoryView.loadData(category_list);

            //刷新列表数据
            shouye_listview.setAdapter(new ShouyeAdapter(ShouyeFragment.this.getActivity(), product_list));

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_shouye, container, false);
        ButterKnife.bind(this, view);

        //--------------------------订制导航栏-----------------------------
        nav_center_textview.setBackgroundResource(R.drawable.nav_search_bg);
        nav_left_imageview.setImageResource(R.drawable.erweima_icon);
        nav_left_imageview.setVisibility(View.VISIBLE);
        nav_right_imageview.setImageResource(R.drawable.warn_icon);
        nav_right_imageview.setVisibility(View.VISIBLE);

        //---------------------------listview header--------------------------------
        //banner
        bannerView = new BannerView(this.getActivity(), null);
        //category gridview
        categoryView = new CategoryView(this.getActivity(), null);

        shouye_listview.addHeaderView(bannerView);
        shouye_listview.addHeaderView(categoryView);
        shouye_listview.setHeaderDividersEnabled(false);

        //--------------------------加载网络请求----------------------------
        //加载首页数据
        Networking.getInstance(this.getActivity()).startReq(new HashMap<String, String>(), "shouyeReq");

        return view;
    }


}
