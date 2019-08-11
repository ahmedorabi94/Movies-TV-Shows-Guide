
package com.test.ahmedorabi.movieapp.model.appModels.TvRatingResponse;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Rating {

    @SerializedName("Source")
    private String mSource;
    @SerializedName("Value")
    private String mValue;

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

}
