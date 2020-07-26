package com.wind.user.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "China")
public class ChinaData extends BaseData {
    private String provice;

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }
}
