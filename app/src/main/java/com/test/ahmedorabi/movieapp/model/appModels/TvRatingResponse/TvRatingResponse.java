
package com.test.ahmedorabi.movieapp.model.appModels.TvRatingResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TvRatingResponse {

    @SerializedName("Actors")
    private String mActors;
    @SerializedName("Awards")
    private String mAwards;
    @SerializedName("Country")
    private String mCountry;
    @SerializedName("Director")
    private String mDirector;
    @SerializedName("TvGenre")
    private String mGenre;
    @SerializedName("imdbID")
    private String mImdbID;
    @SerializedName("imdbRating")
    private String mImdbRating;
    @SerializedName("imdbVotes")
    private String mImdbVotes;
    @SerializedName("Language")
    private String mLanguage;
    @SerializedName("Metascore")
    private String mMetascore;
    @SerializedName("Plot")
    private String mPlot;
    @SerializedName("Poster")
    private String mPoster;
    @SerializedName("Rated")
    private String mRated;
    @SerializedName("Ratings")
    private List<Rating> mRatings;
    @SerializedName("Released")
    private String mReleased;
    @SerializedName("Response")
    private String mResponse;
    @SerializedName("Runtime")
    private String mRuntime;
    @SerializedName("Title")
    private String mTitle;
    @SerializedName("totalSeasons")
    private String mTotalSeasons;
    @SerializedName("Type")
    private String mType;
    @SerializedName("Writer")
    private String mWriter;
    @SerializedName("Year")
    private String mYear;

    public String getActors() {
        return mActors;
    }

    public void setActors(String actors) {
        mActors = actors;
    }

    public String getAwards() {
        return mAwards;
    }

    public void setAwards(String awards) {
        mAwards = awards;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getDirector() {
        return mDirector;
    }

    public void setDirector(String director) {
        mDirector = director;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getImdbID() {
        return mImdbID;
    }

    public void setImdbID(String imdbID) {
        mImdbID = imdbID;
    }

    public String getImdbRating() {
        return mImdbRating;
    }

    public void setImdbRating(String imdbRating) {
        mImdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return mImdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        mImdbVotes = imdbVotes;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getMetascore() {
        return mMetascore;
    }

    public void setMetascore(String metascore) {
        mMetascore = metascore;
    }

    public String getPlot() {
        return mPlot;
    }

    public void setPlot(String plot) {
        mPlot = plot;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }

    public String getRated() {
        return mRated;
    }

    public void setRated(String rated) {
        mRated = rated;
    }

    public List<Rating> getRatings() {
        return mRatings;
    }

    public void setRatings(List<Rating> ratings) {
        mRatings = ratings;
    }

    public String getReleased() {
        return mReleased;
    }

    public void setReleased(String released) {
        mReleased = released;
    }

    public String getResponse() {
        return mResponse;
    }

    public void setResponse(String response) {
        mResponse = response;
    }

    public String getRuntime() {
        return mRuntime;
    }

    public void setRuntime(String runtime) {
        mRuntime = runtime;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTotalSeasons() {
        return mTotalSeasons;
    }

    public void setTotalSeasons(String totalSeasons) {
        mTotalSeasons = totalSeasons;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getWriter() {
        return mWriter;
    }

    public void setWriter(String writer) {
        mWriter = writer;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

}
