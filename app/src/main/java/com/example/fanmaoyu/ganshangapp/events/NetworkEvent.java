package com.example.fanmaoyu.ganshangapp.events;

import com.google.gson.JsonElement;

/**
 * Created by fanmaoyu on 2017/9/27.
 */

public class NetworkEvent {
    private String code;
    private String msg;
    private JsonElement data;

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    private int what;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public NetworkEvent(String code, String msg, JsonElement data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }



    public static class LoginBtnEvent extends NetworkEvent{
        public LoginBtnEvent(String code, String msg, JsonElement data) {
            super(code, msg, data);
        }
    }

    public static class ShouyeReqEvent extends NetworkEvent{
        public ShouyeReqEvent(String code, String msg, JsonElement data) {
            super(code, msg, data);
        }
    }

    public static class GouwucheReqEvent extends NetworkEvent{
        public GouwucheReqEvent(String code, String msg, JsonElement data) {
            super(code, msg, data);
        }
    }
}
