package com.example.fanmaoyu.ganshangapp.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fanmaoyu.ganshangapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanmaoyu on 2017/9/25.
 */

public class RegisterActivity extends AppCompatActivity{

    @BindView(R.id.nav_center_textview)
    TextView nav_title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        nav_title.setText("注册");

    }
}
