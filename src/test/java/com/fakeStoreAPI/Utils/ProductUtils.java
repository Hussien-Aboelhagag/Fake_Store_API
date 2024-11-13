package com.fakeStoreAPI.Utils;

import com.fakeStoreAPI.POJO_Class.AddProduct;
import com.github.javafaker.Faker;

public class ProductUtils {
    public AddProduct productDetails(){
        String title=new Faker().name().title();
        int price=new Faker().number().randomDigit();
        String description=new Faker().commerce().material();
        String image=new Faker().avatar().image();
        String category=new Faker().commerce().department();
        AddProduct addProduct=new AddProduct(title,price,description,image,category);
        return addProduct;
    }
}
