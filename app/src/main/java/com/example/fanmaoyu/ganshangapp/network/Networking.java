package com.example.fanmaoyu.ganshangapp.network;

import com.example.fanmaoyu.ganshangapp.constant.ServiceConstant;
import com.example.fanmaoyu.ganshangapp.tools.MathUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by fanmaoyu on 2017/9/20.
 */

public class Networking{

    public interface NetResponseInterface{
        void successCallback(JsonElement jsonElement);
        void failCallback(String errormsg);
    }

    private static Networking networking = null;
    private NetService service;
    private NetResponseInterface callback;

    public Networking(NetResponseInterface callback){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ServiceConstant.ServiceIP)
//                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(NetService.class);
        this.callback = callback;
    }

    private HashMap<String, String> sortParams(HashMap<String, String> params){
        long timestamp = new Date().getTime();
        params.put("time", String.valueOf(timestamp));
        Object[] keyArray =  params.keySet().toArray();
        Arrays.sort(keyArray);

        String str = "";
        for(Object key : keyArray){
            str += (String)key + '-' + params.get(key) + '-';
        }
        str += ServiceConstant.SignKey;
        String str_md5 = MathUtils.MD5(str).toUpperCase();

        params.put("sign", str_md5);

        return params;
    }

    public void startReq(HashMap<String, String> params, String serviceName){

        HashMap<String, String> sortedParams = this.sortParams(params);
        Call<ResponseBody> call = null;

        if(serviceName.equals("shouyeReq")){
            call = this.service.shouyeReq(sortedParams);
        }else if(serviceName.equals("categoryReq")){
            call = this.service.categoryReq(sortedParams);
        }else if(serviceName.equals("gouwucheReq")){
            call = this.service.gouwucheReq(sortedParams);
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String responseJson = null;
                try {
                    responseJson = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(responseJson).getAsJsonObject();

                final String code = jsonObject.get("code").getAsString();
                final String msg = jsonObject.get("msg").getAsString();
                final JsonElement jsonElement = jsonObject.get("data");

                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        if(code.equals("Succ")){
                            Networking.this.callback.successCallback(jsonElement);
                        }else{
                            Networking.this.callback.failCallback(msg);
                        }
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }


}
