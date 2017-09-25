package com.example.fanmaoyu.ganshangapp.network;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by fanmaoyu on 2017/9/20.
 */

public interface NetService {
    @FormUrlEncoded
    @POST("index/HomePage/")
    Call<ResponseBody> shouyeReq(@FieldMap HashMap<String, String> params);


    @FormUrlEncoded
    @POST("index/ClassIfication/")
    Call<ResponseBody> categoryReq(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("order/ShopCart/")
    Call<ResponseBody> gouwucheReq(@FieldMap HashMap<String, String> params);
}
