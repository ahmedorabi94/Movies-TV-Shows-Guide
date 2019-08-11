package com.test.ahmedorabi.movieapp.repository.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class Movie {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "movie_id")
    private String movieId;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @ColumnInfo(name = "overview")
    private String overView;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "vote_average")
    public String voteAverage;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "review")
    private String review;

    @ColumnInfo(name = "back_drop")
    private String backDropPath;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "language")
    private String language;

    @ColumnInfo(name = "vote_count")
    private String voteCount;

    public Movie() {

    }

    public Movie(String movieId, String title, String imageUrl, String overView, String releaseDate, String voteAverage, String author, String review, String backDropPath, String type, String language, String voteCount) {
        this.movieId = movieId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.overView = overView;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.author = author;
        this.review = review;
        this.backDropPath = backDropPath;
        this.type = type;
        this.language = language;
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }
}
