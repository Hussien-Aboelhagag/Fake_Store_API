package com.fakeStoreAPI.POJO_Class;

import java.util.List;

public class AddCart {
    private int userId;
    private String date;
    private List<productInCart> products;

    public AddCart(int userId,String date,List<productInCart>products){
        this.userId=userId;
        this.date=date;
        this.products=products;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<productInCart> getProducts() {
        return products;
    }

    public void setProducts(List<productInCart> products) {
        this.products = products;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
