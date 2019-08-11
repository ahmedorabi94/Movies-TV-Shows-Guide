
package com.test.ahmedorabi.movieapp.repository.data.knownResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class KnownResponse {

    @SerializedName("cast")
    private List<Cast> mCast;
    @SerializedName("crew")
    private List<Crew> mCrew;
    @SerializedName("id")
    private Long mId;

    public List<Cast> getCast() {
        return mCast;
    }

    public void setCast(List<Cast> cast) {
        mCast = cast;
    }

    public List<Crew> getCrew() {
        return mCrew;
    }

    public void setCrew(List<Crew> crew) {
        mCrew = crew;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

}
