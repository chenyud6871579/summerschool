package com.wind.user.pojo;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Beijing")
public class BeijingData extends BaseData{
    private String city;


    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    
}
