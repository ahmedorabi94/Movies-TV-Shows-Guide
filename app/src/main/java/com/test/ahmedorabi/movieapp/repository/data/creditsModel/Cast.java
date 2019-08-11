
package com.test.ahmedorabi.movieapp.repository.data.creditsModel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Cast {

    @SerializedName("cast_id")
    private Long mCastId;
    @SerializedName("character")
    public String mCharacter;
    @SerializedName("credit_id")
    private String mCreditId;
    @SerializedName("gender")
    private Long mGender;
    @SerializedName("id")
    public int mId;
    @SerializedName("name")
    public String mName;
    @SerializedName("order")
    private Long mOrder;
    @SerializedName("profile_path")
    public String mProfilePath;

    public Long getCastId() {
        return mCastId;
    }

    public void setCastId(Long castId) {
        mCastId = castId;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public void setCharacter(String character) {
        mCharacter = character;
    }

    public String getCreditId() {
        return mCreditId;
    }

    public void setCreditId(String creditId) {
        mCreditId = creditId;
    }

    public Long getGender() {
        return mGender;
    }

    public void setGender(Long gender) {
        mGender = gender;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getOrder() {
        return mOrder;
    }

    public void setOrder(Long order) {
        mOrder = order;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

}
