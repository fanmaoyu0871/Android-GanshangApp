package com.example.fanmaoyu.ganshangapp.fragments;

import android.app.Activity;
import android.content.Intent;
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
import com.example.fanmaoyu.ganshangapp.activitys.LoginActivity;
import com.example.fanmaoyu.ganshangapp.adapters.WodeAdapter;
import com.example.fanmaoyu.ganshangapp.events.PageEvent;
import com.example.fanmaoyu.ganshangapp.models.UserModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanmaoyu on 2017/9/18.
 */

public class WodeFragment extends Fragment {
    @BindView(R.id.nav_center_textview)
    TextView nav_center_textview;

    @BindView(R.id.nav_left_imageview)
    ImageView nav_left_imageview;

    @BindView(R.id.wode_listview)
    ListView wode_listview;

    private HeaderView headerView;

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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(PageEvent.UserInfoEvent event){

        UserModel userModel = UserModel.getInstance();

        if(userModel.getId() != 0) {
            this.headerView.wode_name.setText(userModel.getNickName());
            this.headerView.login_textview.setVisibility(View.INVISIBLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_wode, container, false);
        ButterKnife.bind(this, view);

        nav_left_imageview.setVisibility(View.INVISIBLE);
        nav_center_textview.setText("我的");

        View mineHeaderView = LayoutInflater.from(this.getActivity()).inflate(R.layout.view_mineheader, wode_listview, false);
        this.headerView = new HeaderView(this.getActivity(), mineHeaderView);
        this.headerView.bindEvent();

        wode_listview.addHeaderView(mineHeaderView);
        wode_listview.setHeaderDividersEnabled(false);
        wode_listview.setAdapter(new WodeAdapter(this.getActivity()));

        return view;
    }


    public class HeaderView{
        @BindView(R.id.wode_name)
        TextView wode_name;

        @BindView(R.id.login_textview)
        TextView login_textview;

        @BindView(R.id.myMsgBtn)
        LinearLayout myMsgBtn;

        @BindView(R.id.myFavirateBtn)
        LinearLayout myFavirateBtn;

        private Activity context;

        public HeaderView(final Activity context, View mineHeaderView) {
            this.context = context;
            ButterKnife.bind(this, mineHeaderView);
        }

        public void bindEvent(){
            this.login_textview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivityForResult(new Intent(context, LoginActivity.class), 100);
                }
            });

            this.myMsgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            this.myFavirateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
