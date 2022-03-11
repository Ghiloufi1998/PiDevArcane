/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Acer
 */
public class Stat {
    private int count;
    private double avgprix;
    private String maxh;
    private String maxT; 
    private String maxv;
    private int  countt;
    private int counth;
    private int countv;
    public Stat() {
    }

    public Stat(int count, double avgprix, String maxh, String maxT, String maxv, int countt, int counth, int countv) {
        this.count = count;
        this.avgprix = avgprix;
        this.maxh = maxh;
        this.maxT = maxT;
        this.maxv = maxv;
        this.countt = countt;
        this.counth = counth;
        this.countv = countv;
    }

    public int getCountt() {
        return countt;
    }

    public void setCountt(int countt) {
        this.countt = countt;
    }

    public int getCounth() {
        return counth;
    }

    public void setCounth(int counth) {
        this.counth = counth;
    }

    public int getCountv() {
        return countv;
    }

    public void setCountv(int countv) {
        this.countv = countv;
    }

    

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAvgprix() {
        return avgprix;
    }

    public void setAvgprix(double avgprix) {
        this.avgprix = avgprix;
    }

    public String getMaxh() {
        return maxh;
    }

    public void setMaxh(String maxh) {
        this.maxh = maxh;
    }

    public String getMaxT() {
        return maxT;
    }

    public void setMaxT(String maxT) {
        this.maxT = maxT;
    }

    public String getMaxv() {
        return maxv;
    }

    public void setMaxv(String maxv) {
        this.maxv = maxv;
    }

    @Override
    public String toString() {
        return "Stat{" + "count=" + count + ", avgprix=" + avgprix + ", maxh=" + maxh + ", maxT=" + maxT + ", maxv=" + maxv + ", countt=" + countt + ", counth=" + counth + ", countv=" + countv + '}';
    }

    

}