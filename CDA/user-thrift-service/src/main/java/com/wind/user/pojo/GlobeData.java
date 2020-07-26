package com.wind.user.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Globe")
public class GlobeData extends BaseData {
    private String country;
    private String countryCode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
