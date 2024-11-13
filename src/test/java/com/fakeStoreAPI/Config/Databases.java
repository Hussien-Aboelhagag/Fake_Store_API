package com.fakeStoreAPI.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Databases {
    public static String[] USERNAME_DATABASE = {"johnd", "mor_2314", "kevinryan", "donero"
            , "derek", "david_r", "snyder", "hopkins", "kate_h", "jimmie_k"};

    public static String[] PRODUCT_TITLE_DATABASE = {"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
            , "Mens Casual Premium Slim Fit T-Shirts "
            , "Mens Cotton Jacket", "Mens Casual Slim Fit", "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet"
            , "Solid Gold Petite Micropave ", "White Gold Plated Princess",
            "Pierced Owl Rose Gold Plated Stainless Steel Double"
            , "WD 2TB Elements Portable External Hard Drive - USB 3.0 "
            , "SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s"
            , "Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5",
            "WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive"
            , "Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin",
            "Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultrawide Screen QLED ",
            "BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats"
            , "Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket"
            , "Rain Jacket Women Windbreaker Striped Climbing Raincoats"
            , "MBJ Women's Solid Short Sleeve Boat Neck V "
            , "Opna Women's Short Sleeve Moisture", "DANVOUY Womens T Shirt Casual Cotton Short"};


    public static String[] PRODUCT_CATEGORY_DATABASE = {"men's clothing", "men's clothing",
            "men's clothing", "men's clothing", "jewelery", "jewelery", "jewelery", "jewelery"
            , "electronics", "electronics", "electronics", "electronics", "electronics", "electronics",
            "women's clothing", "women's clothing", "women's clothing", "women's clothing", "women's clothing", "women's clothing"};

    public static String readJsonFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}