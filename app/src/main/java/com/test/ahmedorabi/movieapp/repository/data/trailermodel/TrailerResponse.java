
package com.test.ahmedorabi.movieapp.repository.data.trailermodel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TrailerResponse {

    @SerializedName("id")
    private Long mId;
    @SerializedName("results")
    private List<TrailerResult> mResults;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<TrailerResult> getResults() {
        return mResults;
    }

    public void setResults(List<TrailerResult> results) {
        mResults = results;
    }

}
