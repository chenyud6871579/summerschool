package com.wind.user.pojo;

import java.io.Serializable;

public class BaseData implements Serializable {
    private Integer confirmed;
    private Integer cured;
    private String date;
    private Integer dead;
    private Integer suspected;

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getCured() {
        return cured;
    }

    public void setCured(Integer cured) {
        this.cured = cured;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDead() {
        return dead;
    }

    public void setDead(Integer dead) {
        this.dead = dead;
    }

    public Integer getSuspected() {
        return suspected;
    }

    public void setSuspected(Integer suspected) {
        this.suspected = suspected;
    }
}
