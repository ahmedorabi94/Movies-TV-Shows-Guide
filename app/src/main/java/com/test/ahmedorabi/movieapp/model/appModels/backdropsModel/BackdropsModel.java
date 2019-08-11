
package com.test.ahmedorabi.movieapp.model.appModels.backdropsModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class BackdropsModel {

    @SerializedName("backdrops")
    private List<Backdrop> mBackdrops;
    @SerializedName("id")
    private Long mId;
    @SerializedName("posters")
    private List<Poster> mPosters;

    public List<Backdrop> getBackdrops() {
        return mBackdrops;
    }

    public void setBackdrops(List<Backdrop> backdrops) {
        mBackdrops = backdrops;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<Poster> getPosters() {
        return mPosters;
    }

    public void setPosters(List<Poster> posters) {
        mPosters = posters;
    }

}
