
package com.test.ahmedorabi.movieapp.model.appModels.genresmodel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ProductionCountry {

    @SerializedName("iso_3166_1")
    private String mIso31661;
    @SerializedName("name")
    private String mName;

    public String getIso31661() {
        return mIso31661;
    }

    public void setIso31661(String iso31661) {
        mIso31661 = iso31661;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
