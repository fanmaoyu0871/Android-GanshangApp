package com.example.fanmaoyu.ganshangapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by fanmaoyu on 2017/9/20.
 */

public class ShouyeModel implements Serializable {

    public static class CategoryModel implements Parcelable {

        /**
         * id : 43
         * name : 打印机/耗材
         * image : assets/imgview/attribute/20170720/2c920f3f5d31a20d015d5f88641a010e.jpg
         */

        private String id;
        private String name;
        private String image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.image);
        }

        public CategoryModel() {
        }

        protected CategoryModel(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.image = in.readString();
        }

        public static final Parcelable.Creator<CategoryModel> CREATOR = new Parcelable.Creator<CategoryModel>() {
            @Override
            public CategoryModel createFromParcel(Parcel source) {
                return new CategoryModel(source);
            }

            @Override
            public CategoryModel[] newArray(int size) {
                return new CategoryModel[size];
            }
        };
    }

    public static class Product implements Parcelable {

        /**
         * id : 59
         * collectCount : 1
         * clinchCount : 0
         * ideas : 维融-中国十大点钞机品牌
         * price : 1780.00
         * inventory : 218
         * name : 维融M6B类点验钞机支持新版人民币
         * minimumPrice : 799.00
         * image : assets/imgview/production/20170726/2c920f3f5d7d65e5015d7d8d95220009.jpg
         * collect : false
         */

        private String id;
        private int collectCount;
        private int clinchCount;
        private String ideas;
        private String price;
        private int inventory;
        private String name;
        private String minimumPrice;
        private String image;
        private boolean collect;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public int getClinchCount() {
            return clinchCount;
        }

        public void setClinchCount(int clinchCount) {
            this.clinchCount = clinchCount;
        }

        public String getIdeas() {
            return ideas;
        }

        public void setIdeas(String ideas) {
            this.ideas = ideas;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMinimumPrice() {
            return minimumPrice;
        }

        public void setMinimumPrice(String minimumPrice) {
            this.minimumPrice = minimumPrice;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeInt(this.collectCount);
            dest.writeInt(this.clinchCount);
            dest.writeString(this.ideas);
            dest.writeString(this.price);
            dest.writeInt(this.inventory);
            dest.writeString(this.name);
            dest.writeString(this.minimumPrice);
            dest.writeString(this.image);
            dest.writeByte(this.collect ? (byte) 1 : (byte) 0);
        }

        public Product() {
        }

        protected Product(Parcel in) {
            this.id = in.readString();
            this.collectCount = in.readInt();
            this.clinchCount = in.readInt();
            this.ideas = in.readString();
            this.price = in.readString();
            this.inventory = in.readInt();
            this.name = in.readString();
            this.minimumPrice = in.readString();
            this.image = in.readString();
            this.collect = in.readByte() != 0;
        }

        public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
            @Override
            public Product createFromParcel(Parcel source) {
                return new Product(source);
            }

            @Override
            public Product[] newArray(int size) {
                return new Product[size];
            }
        };
    }


}
