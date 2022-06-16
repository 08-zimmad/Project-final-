package com.example.foodapp;

public class model {
    String foodName,foodPrice,purl;

    public model(String foodName, String foodPrice, String purl) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.purl = purl;
    }
    public model()
    {

    }


    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }


}
