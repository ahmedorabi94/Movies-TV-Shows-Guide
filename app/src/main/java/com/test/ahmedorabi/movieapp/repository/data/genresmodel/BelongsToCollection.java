
package com.test.ahmedorabi.movieapp.repository.data.genresmodel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class BelongsToCollection {

    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("poster_path")
    private String mPosterPath;

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

}
