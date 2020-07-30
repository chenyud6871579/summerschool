package com.wind.user.bean;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OutDataBean {
    private String name;
    private String code;
    private List<Integer> date;
    private List<Integer> confirmed;
    private List<Integer> cured;
    private List<Integer> dead;
    private List<Integer> suspected;
    private List<Double> radarList;

    public OutDataBean() {

    }

    public OutDataBean(String _id, String code, List<Integer> date, List<Integer> confirmed, List<Integer> cured, List<Integer> dead, List<Integer> suspected) {
        this.name = _id;
        this.code = code;
        this.date = date;
        this.confirmed = confirmed;
        this.cured = cured;
        this.dead = dead;
        this.suspected = suspected;
    }

    public OutDataBean(String name, String code, List<Integer> date, List<Integer> confirmed, List<Integer> cured, List<Integer> dead, List<Integer> suspected, List<Double> radarList) {
        this.name = name;
        this.code = code;
        this.date = date;
        this.confirmed = confirmed;
        this.cured = cured;
        this.dead = dead;
        this.suspected = suspected;
        this.radarList = radarList;
    }

    public List<Double> getRadarList() {
        return radarList;
    }

    public void setRadarList(List<Double> radarList) {
        this.radarList = radarList;
    }

    public List<Integer> getSuspected() {
        return suspected;
    }

    public void setSuspected(List<Integer> suspected) {
        this.suspected = suspected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Integer> getDate() {
        return date;
    }

    public void setDate(List<Integer> date) {
        this.date = date;
    }

    public List<Integer> getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(List<Integer> confirmed) {
        this.confirmed = confirmed;
    }

    public List<Integer> getCured() {
        return cured;
    }

    public void setCured(List<Integer> cured) {
        this.cured = cured;
    }

    public List<Integer> getDead() {
        return dead;
    }

    public void setDead(List<Integer> dead) {
        this.dead = dead;
    }

    @Override
    public String toString() {
        return "OutDataBean{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", confirmed=" + confirmed +
                ", cured=" + cured +
                ", dead=" + dead +
                ", suspected=" + suspected +
                ", radarList=" + radarList +
                '}';
    }

    public String myToString(){
        return "{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", confirmed=" + confirmed +
                ", cured=" + cured +
                ", dead=" + dead +
                ", suspected=" + suspected +
                ", radarList=" + radarList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutDataBean that = (OutDataBean) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
