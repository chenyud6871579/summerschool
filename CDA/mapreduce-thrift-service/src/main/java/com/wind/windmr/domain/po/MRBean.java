package com.wind.windmr.domain.po;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

public class MRBean implements Writable {

    private List<Integer> confirmed;
    private List<Integer> cured;
    private List<Integer> dead;
    private List<Integer> suspected;
    private long population;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public MRBean(List<Integer> confirmed, List<Integer> cured, List<Integer> dead, List<Integer> suspected, long population, String flag) {
        this.confirmed = confirmed;
        this.cured = cured;
        this.dead = dead;
        this.suspected = suspected;
        this.population = population;
        this.flag = flag;
    }

    public MRBean() {
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

    public List<Integer> getSuspected() {
        return suspected;
    }

    public void setSuspected(List<Integer> suspected) {
        this.suspected = suspected;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(JSON.toJSONString(confirmed));
        dataOutput.writeUTF(JSON.toJSONString(cured));
        dataOutput.writeUTF(JSON.toJSONString(dead));
        dataOutput.writeUTF(JSON.toJSONString(suspected));
        dataOutput.writeLong(population);
        dataOutput.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.confirmed = JSON.parseArray(dataInput.readUTF(),Integer.class);
        this.cured = JSON.parseArray(dataInput.readUTF(),Integer.class);
        this.dead = JSON.parseArray(dataInput.readUTF(),Integer.class);
        this.suspected = JSON.parseArray(dataInput.readUTF(),Integer.class);
        this.population = dataInput.readLong();
        this.flag = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return "MRBean{" +
                "confirmed=" + confirmed +
                ", cured=" + cured +
                ", dead=" + dead +
                ", suspected=" + suspected +
                ", population=" + population +
                ", flag='" + flag + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
