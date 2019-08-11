
package com.test.ahmedorabi.movieapp.repository.data.IDResponse;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class IdResponse {

    @SerializedName("facebook_id")
    private String mFacebookId;
    @SerializedName("freebase_id")
    private String mFreebaseId;
    @SerializedName("freebase_mid")
    private String mFreebaseMid;
    @SerializedName("id")
    private Long mId;
    @SerializedName("imdb_id")
    private String mImdbId;
    @SerializedName("instagram_id")
    private String mInstagramId;
    @SerializedName("tvdb_id")
    private Long mTvdbId;
    @SerializedName("tvrage_id")
    private Long mTvrageId;
    @SerializedName("twitter_id")
    private String mTwitterId;

    public String getFacebookId() {
        return mFacebookId;
    }

    public void setFacebookId(String facebookId) {
        mFacebookId = facebookId;
    }

    public String getFreebaseId() {
        return mFreebaseId;
    }

    public void setFreebaseId(String freebaseId) {
        mFreebaseId = freebaseId;
    }

    public String getFreebaseMid() {
        return mFreebaseMid;
    }

    public void setFreebaseMid(String freebaseMid) {
        mFreebaseMid = freebaseMid;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImdbId() {
        return mImdbId;
    }

    public void setImdbId(String imdbId) {
        mImdbId = imdbId;
    }

    public String getInstagramId() {
        return mInstagramId;
    }

    public void setInstagramId(String instagramId) {
        mInstagramId = instagramId;
    }

    public Long getTvdbId() {
        return mTvdbId;
    }

    public void setTvdbId(Long tvdbId) {
        mTvdbId = tvdbId;
    }

    public Long getTvrageId() {
        return mTvrageId;
    }

    public void setTvrageId(Long tvrageId) {
        mTvrageId = tvrageId;
    }

    public String getTwitterId() {
        return mTwitterId;
    }

    public void setTwitterId(String twitterId) {
        mTwitterId = twitterId;
    }

}
