
package com.test.ahmedorabi.movieapp.repository.data.TvGenresResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TvGenresResponse {

    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("created_by")
    private List<CreatedBy> mCreatedBy;
    @SerializedName("episode_run_time")
    private List<Long> mEpisodeRunTime;
    @SerializedName("first_air_date")
    private String mFirstAirDate;
    @SerializedName("genres")
    private List<Genre> mGenres;
    @SerializedName("homepage")
    private String mHomepage;
    @SerializedName("id")
    private Long mId;
    @SerializedName("in_production")
    private Boolean mInProduction;
    @SerializedName("languages")
    private List<String> mLanguages;
    @SerializedName("last_air_date")
    private String mLastAirDate;
    @SerializedName("last_episode_to_air")
    private LastEpisodeToAir mLastEpisodeToAir;
    @SerializedName("name")
    private String mName;
    @SerializedName("networks")
    private List<Network> mNetworks;
    @SerializedName("next_episode_to_air")
    private Object mNextEpisodeToAir;
    @SerializedName("number_of_episodes")
    private Long mNumberOfEpisodes;
    @SerializedName("number_of_seasons")
    private Long mNumberOfSeasons;
    @SerializedName("origin_country")
    private List<String> mOriginCountry;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_name")
    private String mOriginalName;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("production_companies")
    private List<ProductionCompany> mProductionCompanies;
    @SerializedName("seasons")
    private List<Season> mSeasons;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("type")
    private String mType;
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @SerializedName("vote_count")
    private Long mVoteCount;

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public List<CreatedBy> getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(List<CreatedBy> createdBy) {
        mCreatedBy = createdBy;
    }

    public List<Long> getEpisodeRunTime() {
        return mEpisodeRunTime;
    }

    public void setEpisodeRunTime(List<Long> episodeRunTime) {
        mEpisodeRunTime = episodeRunTime;
    }

    public String getFirstAirDate() {
        return mFirstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        mFirstAirDate = firstAirDate;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Boolean getInProduction() {
        return mInProduction;
    }

    public void setInProduction(Boolean inProduction) {
        mInProduction = inProduction;
    }

    public List<String> getLanguages() {
        return mLanguages;
    }

    public void setLanguages(List<String> languages) {
        mLanguages = languages;
    }

    public String getLastAirDate() {
        return mLastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        mLastAirDate = lastAirDate;
    }

    public LastEpisodeToAir getLastEpisodeToAir() {
        return mLastEpisodeToAir;
    }

    public void setLastEpisodeToAir(LastEpisodeToAir lastEpisodeToAir) {
        mLastEpisodeToAir = lastEpisodeToAir;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Network> getNetworks() {
        return mNetworks;
    }

    public void setNetworks(List<Network> networks) {
        mNetworks = networks;
    }

    public Object getNextEpisodeToAir() {
        return mNextEpisodeToAir;
    }

    public void setNextEpisodeToAir(Object nextEpisodeToAir) {
        mNextEpisodeToAir = nextEpisodeToAir;
    }

    public Long getNumberOfEpisodes() {
        return mNumberOfEpisodes;
    }

    public void setNumberOfEpisodes(Long numberOfEpisodes) {
        mNumberOfEpisodes = numberOfEpisodes;
    }

    public Long getNumberOfSeasons() {
        return mNumberOfSeasons;
    }

    public void setNumberOfSeasons(Long numberOfSeasons) {
        mNumberOfSeasons = numberOfSeasons;
    }

    public List<String> getOriginCountry() {
        return mOriginCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        mOriginCountry = originCountry;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return mOriginalName;
    }

    public void setOriginalName(String originalName) {
        mOriginalName = originalName;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return mProductionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        mProductionCompanies = productionCompanies;
    }

    public List<Season> getSeasons() {
        return mSeasons;
    }

    public void setSeasons(List<Season> seasons) {
        mSeasons = seasons;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
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
