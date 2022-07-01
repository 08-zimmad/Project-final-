package com.example.server_foodapp;

public class model {
    String name,price,purl;

    public model(String foodName, String foodPrice, String purl) {
        this.name = foodName;
        this.price = foodPrice;
        this.purl = purl;
    }
    public model()
    {

    }


    public String getName() {
        return name;
    }

    public void setName(String foodName) {
        this.name = foodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String foodPrice) {
        this.price = foodPrice;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }


}
