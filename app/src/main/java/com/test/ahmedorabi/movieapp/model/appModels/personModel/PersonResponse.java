
package com.test.ahmedorabi.movieapp.model.appModels.personModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PersonResponse {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("also_known_as")
    private List<String> mAlsoKnownAs;
    @SerializedName("biography")
    public String mBiography;
    @SerializedName("birthday")
    public String mBirthday;
    @SerializedName("deathday")
    private Object mDeathday;
    @SerializedName("gender")
    private Long mGender;
    @SerializedName("homepage")
    private Object mHomepage;
    @SerializedName("id")
    private Long mId;
    @SerializedName("imdb_id")
    private String mImdbId;
    @SerializedName("known_for_department")
    private String mKnownForDepartment;
    @SerializedName("name")
    public String mName;
    @SerializedName("place_of_birth")
    public String mPlaceOfBirth;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("profile_path")
    private String mProfilePath;

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public List<String> getAlsoKnownAs() {
        return mAlsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        mAlsoKnownAs = alsoKnownAs;
    }

    public String getBiography() {
        return mBiography;
    }

    public void setBiography(String biography) {
        mBiography = biography;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String birthday) {
        mBirthday = birthday;
    }

    public Object getDeathday() {
        return mDeathday;
    }

    public void setDeathday(Object deathday) {
        mDeathday = deathday;
    }

    public Long getGender() {
        return mGender;
    }

    public void setGender(Long gender) {
        mGender = gender;
    }

    public Object getHomepage() {
        return mHomepage;
    }

    public void setHomepage(Object homepage) {
        mHomepage = homepage;
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

    public String getKnownForDepartment() {
        return mKnownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        mKnownForDepartment = knownForDepartment;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPlaceOfBirth() {
        return mPlaceOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        mPlaceOfBirth = placeOfBirth;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

}
