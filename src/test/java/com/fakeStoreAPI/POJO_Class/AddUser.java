package com.fakeStoreAPI.POJO_Class;

public class AddUser {
    private String email;
    private String username;
    private String password;
    private Name name;
    private AddressClass address;
    private String phone;

    public AddUser(String email,String username,String password,String phone,Name name,AddressClass address){
        this.email=email;
        this.username=username;
        this.password=password;
        this.name=name;
        this.address=address;
        this.phone=phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public AddressClass getAddress() {
        return address;
    }

    public void setAddress(AddressClass address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
