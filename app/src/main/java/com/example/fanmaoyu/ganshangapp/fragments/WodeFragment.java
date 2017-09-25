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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_wode, container, false);
        ButterKnife.bind(this, view);

        nav_left_imageview.setVisibility(View.INVISIBLE);
        nav_center_textview.setText("我的");

        View mineHeaderView = LayoutInflater.from(this.getActivity()).inflate(R.layout.view_mineheader, wode_listview, false);
//        View mineHeaderView = View.inflate(this.getActivity(), R.layout.view_mineheader, null);
        wode_listview.addHeaderView(mineHeaderView);


        return view;
    }
}
