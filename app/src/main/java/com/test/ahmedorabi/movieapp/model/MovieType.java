package com.test.ahmedorabi.movieapp.model;

public class MovieType {

    private int id;
    private String type;
    private int mActorId;

    public MovieType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public MovieType(int id, String type,int ActorId) {
        this.id = id;
        this.type = type;
        this.mActorId = ActorId;
    }

    public int getmActorId() {
        return mActorId;
    }

    public void setmActorId(int mActorId) {
        this.mActorId = mActorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
