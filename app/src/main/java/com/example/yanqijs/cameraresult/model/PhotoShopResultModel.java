package com.example.yanqijs.cameraresult.model;

/**
 * Created by yanqijs on 2016/4/12.
 */
public class PhotoShopResultModel {
    private String name;
    private String imageUrl;
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isZiying() {
        return isZiying;
    }

    public void setZiying(boolean ziying) {
        this.isZiying = ziying;
    }

    private boolean isZiying;
}
