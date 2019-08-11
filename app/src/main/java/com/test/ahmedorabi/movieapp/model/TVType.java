package com.test.ahmedorabi.movieapp.model;

public class TVType {

    private int id;
    private int sNum;
    private Long eNum;


    public TVType(int id, int sNum, Long eNum) {
        this.id = id;
        this.sNum = sNum;
        this.eNum = eNum;
    }

    public TVType(int id, int sNum) {
        this.id = id;
        this.sNum = sNum;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getsNum() {
        return sNum;
    }

    public void setsNum(int sNum) {
        this.sNum = sNum;
    }

    public Long geteNum() {
        return eNum;
    }

    public void seteNum(Long eNum) {
        this.eNum = eNum;
    }
}
