package com.example.fanmaoyu.ganshangapp.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fanmaoyu.ganshangapp.R;
import com.example.fanmaoyu.ganshangapp.constant.UserinfoConstant;
import com.example.fanmaoyu.ganshangapp.events.NetworkEvent;
import com.example.fanmaoyu.ganshangapp.events.PageEvent;
import com.example.fanmaoyu.ganshangapp.models.UserModel;
import com.example.fanmaoyu.ganshangapp.network.Networking;
import com.example.fanmaoyu.ganshangapp.tools.MathUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fanmaoyu on 2017/9/25.
 */

public class LoginActivity extends AppCompatActivity{

    @BindView(R.id.nav_center_textview)
    TextView nav_title;

    @BindView(R.id.nav_right_textview)
    TextView nav_right_title;

    @BindView(R.id.nav_right_imageview)
    ImageView nav_right_imageView;

    @BindView(R.id.login_phone_editText)
    EditText login_phone_editText;

    @BindView(R.id.login_pwd_editText)
    EditText login_pwd_editText;

    private SharedPreferences sharedPreferences;

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
    public void onMessageEvent(NetworkEvent.LoginBtnEvent event) {
        String code = event.getCode();

        if (code.equals("Succ")) {

            JsonElement jsonElement = event.getData();

            if(jsonElement != null && jsonElement.isJsonObject()){
                Gson gson = new Gson();
                UserModel userModel = gson.fromJson(jsonElement, UserModel.class );
                UserModel singleUserModel = UserModel.getInstance();
                singleUserModel.setId(userModel.getId());
                singleUserModel.setHeadpic(userModel.getHeadpic());
                singleUserModel.setNickName(userModel.getNickName());

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(UserinfoConstant.Nickname, userModel.getNickName()); //昵称
                editor.putString(UserinfoConstant.UserImageUrl, userModel.getHeadpic()); //头像
                editor.putInt(UserinfoConstant.UserId, userModel.getId()); //id
                editor.commit();

                EventBus.getDefault().postSticky(new PageEvent.UserInfoEvent());

                finish();
            }
        }else{
            String errormsg = event.getMsg();
            Toast.makeText(LoginActivity.this, errormsg, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.loginBtn)
    public void loginAction(View view){

        if(TextUtils.isEmpty(login_phone_editText.getText())){
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(login_phone_editText.getText().length() != 11){
            Toast.makeText(this, "请输入合法的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(login_pwd_editText.getText())){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }


        //18368808412 123123
        //登录-网络请求
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("mobile", this.login_phone_editText.getText().toString());
        params.put("pwd", MathUtils.MD5(this.login_pwd_editText.getText().toString()));
        Networking.getInstance(this).startReq(params, "loginReq");

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        nav_title.setText("登录");
        nav_right_title.setText("注册");

        nav_right_title.setVisibility(View.VISIBLE);
        nav_right_title.setTextColor(getResources().getColor(R.color.mainThemeColor));
        nav_right_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        this.sharedPreferences = getSharedPreferences(UserinfoConstant.PreferenceKey, Activity.MODE_PRIVATE);

    }
}
