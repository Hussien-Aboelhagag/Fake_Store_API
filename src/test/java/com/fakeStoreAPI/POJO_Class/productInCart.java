package com.fakeStoreAPI.POJO_Class;

public class productInCart {
    private int productId;
    private int quantity;

    public productInCart(int productId,int quantity){
        this.productId=productId;
        this.quantity=quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
