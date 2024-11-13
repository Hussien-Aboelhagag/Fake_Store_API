package com.fakeStoreAPI.Utils;

import com.fakeStoreAPI.POJO_Class.AddCart;
import com.fakeStoreAPI.POJO_Class.productInCart;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CartUtils {
    public productInCart productInCart(){
        int productId=new Faker().number().randomDigit();
        int quantity=new Faker().number().randomDigit();
        productInCart product=new productInCart(productId,quantity);
        return product;
    }
    public AddCart addCart(){
        int userId=new Faker().number().randomDigit();
        String date=new Faker().date().toString();
        List<productInCart> products= new ArrayList<>();
        products.add(productInCart());
        products.add(productInCart());
        AddCart cart=new AddCart(userId,date,products);
        return cart;
    }
}
