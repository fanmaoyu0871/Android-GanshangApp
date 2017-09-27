package com.example.fanmaoyu.ganshangapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fanmaoyu on 2017/9/20.
 */

public class UserModel implements Parcelable {


    /**
     * loginTime : 1506418687635
     * states : 1
     * password : 4297f44b13955235245b2497399d7a93
     * id : 6
     * headpic : assets/imgview/headImg/201706215598.jpg
     * isAgent : Y
     * nickName : 老蒋变态
     * mobile : 18368808412
     */

    private long loginTime;
    private int states;
    private String password;
    private int id;
    private String headpic;
    private String isAgent;
    private String nickName;
    private String mobile;

    static private UserModel userModel;

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.loginTime);
        dest.writeInt(this.states);
        dest.writeString(this.password);
        dest.writeInt(this.id);
        dest.writeString(this.headpic);
        dest.writeString(this.isAgent);
        dest.writeString(this.nickName);
        dest.writeString(this.mobile);
    }

    static public UserModel getInstance(){
        if(userModel == null){
            userModel = new UserModel();
        }

        return userModel;
    }

    private UserModel() {
    }

    protected UserModel(Parcel in) {
        this.loginTime = in.readLong();
        this.states = in.readInt();
        this.password = in.readString();
        this.id = in.readInt();
        this.headpic = in.readString();
        this.isAgent = in.readString();
        this.nickName = in.readString();
        this.mobile = in.readString();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
