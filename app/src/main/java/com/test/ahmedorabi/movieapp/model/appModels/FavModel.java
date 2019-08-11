package com.test.ahmedorabi.movieapp.model.appModels;

public class FavModel {


    private int id;
    private String title;
    public String image;
    private String overview;
    private String releaseDate;
    public double voteAverage;
    private String backdrop_path;
    private String type;
    private String language;
    private long voteCount;

    public FavModel(int id, String title, String image, String overview, String releaseDate, double voteAverage, String backdrop_path, String type, String language, long voteCount) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.backdrop_path = backdrop_path;
        this.type = type;
        this.language = language;
        this.voteCount = voteCount;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }
}

