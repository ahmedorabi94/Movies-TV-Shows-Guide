package com.test.ahmedorabi.movieapp.repository.data;

/**
 * Created by Ahmed Orabi on 15/05/2018.
 */

public class ReveiwItem {

    private String author;
    private String review;

    public ReveiwItem(String author, String review) {
        this.author = author;
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }
}
