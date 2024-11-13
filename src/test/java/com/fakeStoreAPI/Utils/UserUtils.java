package com.fakeStoreAPI.Utils;

import com.fakeStoreAPI.POJO_Class.AddUser;
import com.fakeStoreAPI.POJO_Class.AddressClass;
import com.fakeStoreAPI.POJO_Class.Name;
import com.fakeStoreAPI.POJO_Class.UserAuth;
import com.github.javafaker.Faker;

public class UserUtils {
    public AddressClass addressClass(){
        String city=new Faker().address().city();
        String street=new Faker().address().streetAddress();
        int number= Integer.parseInt(new Faker().address().buildingNumber());
        String zipcode=new Faker().address().zipCode();
        return new AddressClass(city,street,number,zipcode);
    }
    public Name name(){
        String firstname=new Faker().name().firstName();
        String lastname=new Faker().name().lastName();
        return new Name(firstname,lastname);
    }
    public AddUser addUser(){
        String email=new Faker().internet().emailAddress();
        String username=new Faker().name().username();
        String password=new Faker().internet().password();
        String phone=new Faker().phoneNumber().phoneNumber();
        return new AddUser(email,username,password,phone,name(),addressClass());
    }
    public UserAuth auth(){
        String username=new Faker().name().username();
        String password=new Faker().internet().password();
        return new UserAuth(username,password);
    }
}
