
package com.test.ahmedorabi.movieapp.repository.data.imagesResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ImagesResponse {

    @SerializedName("id")
    private Long mId;
    @SerializedName("stills")
    private List<Still> mStills;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<Still> getStills() {
        return mStills;
    }

    public void setStills(List<Still> stills) {
        mStills = stills;
    }

}
