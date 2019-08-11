
package com.test.ahmedorabi.movieapp.model.appModels.seasonResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Episode {

    @SerializedName("air_date")
    private String mAirDate;
    @SerializedName("crew")
    private List<Crew> mCrew;
    @SerializedName("episode_number")
    private Long mEpisodeNumber;
    @SerializedName("guest_stars")
    private List<GuestStar> mGuestStars;
    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("production_code")
    private String mProductionCode;
    @SerializedName("season_number")
    private Long mSeasonNumber;
    @SerializedName("show_id")
    private Long mShowId;
    @SerializedName("still_path")
    public String mStillPath;
    @SerializedName("vote_average")
    public Double mVoteAverage;
    @SerializedName("vote_count")
    private Long mVoteCount;

    public String getAirDate() {
        return mAirDate;
    }

    public void setAirDate(String airDate) {
        mAirDate = airDate;
    }

    public List<Crew> getCrew() {
        return mCrew;
    }

    public void setCrew(List<Crew> crew) {
        mCrew = crew;
    }

    public Long getEpisodeNumber() {
        return mEpisodeNumber;
    }

    public void setEpisodeNumber(Long episodeNumber) {
        mEpisodeNumber = episodeNumber;
    }

    public List<GuestStar> getGuestStars() {
        return mGuestStars;
    }

    public void setGuestStars(List<GuestStar> guestStars) {
        mGuestStars = guestStars;
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

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getProductionCode() {
        return mProductionCode;
    }

    public void setProductionCode(String productionCode) {
        mProductionCode = productionCode;
    }

    public Long getSeasonNumber() {
        return mSeasonNumber;
    }

    public void setSeasonNumber(Long seasonNumber) {
        mSeasonNumber = seasonNumber;
    }

    public Long getShowId() {
        return mShowId;
    }

    public void setShowId(Long showId) {
        mShowId = showId;
    }

    public String getStillPath() {
        return mStillPath;
    }

    public void setStillPath(String stillPath) {
        mStillPath = stillPath;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Long voteCount) {
        mVoteCount = voteCount;
    }

}
