package com.example.fanmaoyu.ganshangapp.models;

/**
 * Created by fanmaoyu on 2017/9/28.
 */

public class GouwucheModel {

    /**
     * modelId : 207
     * id : 264
     * createTime : 1504073244000
     * colorName : 黑色
     * modelName : WR-Q6
     * productionId : 99
     * updateTime : 1504073244000
     * status : 0
     * productsNum : 5
     * colorId : 79
     * memberId : 6
     * production : {"id":"99","collectCount":633,"clinchCount":369,"ideas":"维融-中国十大点钞机品牌","price":"680.00","collectStatus":0,"inventory":126,"name":"维融Q6盘点机无线扫描枪条码数据采集器","minimumPrice":"173.00","image":"assets/imgview/production/20170726/2c920f3f5d7df952015d7e25df630030.jpg","collect":false}
     */

    private int modelId;
    private String id;
    private long createTime;
    private String colorName;
    private String modelName;
    private int productionId;
    private long updateTime;
    private int status;
    private int productsNum;
    private int colorId;
    private int memberId;
    private ProductionBean production;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getProductionId() {
        return productionId;
    }

    public void setProductionId(int productionId) {
        this.productionId = productionId;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProductsNum() {
        return productsNum;
    }

    public void setProductsNum(int productsNum) {
        this.productsNum = productsNum;
    }



    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public ProductionBean getProduction() {
        return production;
    }

    public void setProduction(ProductionBean production) {
        this.production = production;
    }

    public static class ProductionBean {
        /**
         * id : 99
         * collectCount : 633
         * clinchCount : 369
         * ideas : 维融-中国十大点钞机品牌
         * price : 680.00
         * collectStatus : 0
         * inventory : 126
         * name : 维融Q6盘点机无线扫描枪条码数据采集器
         * minimumPrice : 173.00
         * image : assets/imgview/production/20170726/2c920f3f5d7df952015d7e25df630030.jpg
         * collect : false
         */

        private String id;
        private int collectCount;
        private int clinchCount;
        private String ideas;
        private String price;
        private int collectStatus;
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

        public int getCollectStatus() {
            return collectStatus;
        }

        public void setCollectStatus(int collectStatus) {
            this.collectStatus = collectStatus;
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
    }
}
