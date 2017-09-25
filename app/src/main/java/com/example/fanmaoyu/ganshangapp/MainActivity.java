package com.example.fanmaoyu.ganshangapp;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.fanmaoyu.ganshangapp.fragments.GouwucheFragment;
import com.example.fanmaoyu.ganshangapp.fragments.ShouyeFragment;
import com.example.fanmaoyu.ganshangapp.fragments.WodeFragment;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_framelayout)
    FrameLayout container;

    @BindView(R.id.tabbar_radiogroup)
    RadioGroup radioGroup;

    private ShouyeFragment shouyeFragment;
    private GouwucheFragment gouwucheFragment;
    private WodeFragment wodeFragment;
    private HashMap<String, Fragment> fragmentHashMap = new HashMap<String, Fragment>();
    private final FragmentManager fragmentManager = this.getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                    switch (i){
                        case R.id.radio_shouye:
                            MainActivity.this.showFragment("ShouyeFragment");
                            break;
                        case R.id.radio_gouwuche:
                            MainActivity.this.showFragment("GouwucheFragment");
                            break;
                        case R.id.radio_wode:
                            MainActivity.this.showFragment("WodeFragment");
                            break;
                        default:
                            break;
                    }
            }
        });

        this.showFragment("ShouyeFragment");
    }

    private void hideFragment(){
        for(String key : this.fragmentHashMap.keySet()){
            Fragment fragment = this.fragmentHashMap.get(key);
            if(fragment != null){
                FragmentTransaction transaction = this.fragmentManager.beginTransaction();
                transaction.hide(fragment);
                transaction.commit();
            }
        }
    }

    private void showFragment(String fragmentName){

        this.hideFragment();

        try {
            Object object = this.fragmentHashMap.get(fragmentName);
            FragmentTransaction transaction = this.fragmentManager.beginTransaction();

            if(object == null){
                String clsName = getPackageName()+ ".fragments." + fragmentName;
                Class<?> cls =  Class.forName(clsName);
                Fragment fragment = (Fragment) cls.newInstance();
                this.fragmentHashMap.put(fragmentName, fragment);
                transaction.add(R.id.main_framelayout, fragment);
            }else{
                transaction.show((Fragment) object);
            }

            transaction.commit();


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
