package com.example.awl.lab3;

import java.io.Serializable;

/**
 * Created by AWL on 2017/10/24.
 */

public class Goods_data implements Serializable {
    private String firstLetter;
    private int imageId;
    private String type_info;
    private String name;
    private String price;
    private String type;

    public Goods_data(String name, String price, String type, String type_info, int imageId)
    {
        this.name = name;
        this.price = price;
        this.type = type;
        this.type_info = type_info;
        this.imageId = imageId;
        this.firstLetter = (this.name.charAt(0) + "");
    }

    public String getFirstLetter()
    {
        return this.firstLetter;
    }

    public int getImageId()
    {
        return this.imageId;
    }

    public String getInfomation()
    {
        return this.type_info;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPrice()
    {
        return this.price;
    }

    public String getType()
    {
        return this.type;
    }
}
