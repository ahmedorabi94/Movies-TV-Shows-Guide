package com.test.ahmedorabi.movieapp.api;

import androidx.lifecycle.LiveData;

import com.test.ahmedorabi.movieapp.repository.data.ActorImages.ActorImages;
import com.test.ahmedorabi.movieapp.repository.data.IDResponse.IdResponse;
import com.test.ahmedorabi.movieapp.repository.data.RatingResponse.RatingResponse;
import com.test.ahmedorabi.movieapp.repository.data.TvGenresResponse.TvGenresResponse;
import com.test.ahmedorabi.movieapp.repository.data.TvRatingResponse.TvRatingResponse;
import com.test.ahmedorabi.movieapp.repository.data.backdropsModel.BackdropsModel;
import com.test.ahmedorabi.movieapp.repository.data.creditsModel.CreditsResponse;
import com.test.ahmedorabi.movieapp.repository.data.genresmodel.GenresResponse;
import com.test.ahmedorabi.movieapp.repository.data.imagesResponse.ImagesResponse;
import com.test.ahmedorabi.movieapp.repository.data.knownResponse.KnownResponse;
import com.test.ahmedorabi.movieapp.repository.data.moviemodel.MovieResponse;
import com.test.ahmedorabi.movieapp.repository.data.personModel.PersonResponse;
import com.test.ahmedorabi.movieapp.repository.data.reviewModel.ReviewResponse;
import com.test.ahmedorabi.movieapp.repository.data.searchModel.SearchResponse;
import com.test.ahmedorabi.movieapp.repository.data.seasonResponse.SeasonResponse;
import com.test.ahmedorabi.movieapp.repository.data.similar.SimilarResponse;
import com.test.ahmedorabi.movieapp.repository.data.similarTvModel.SimilarTvResponse;
import com.test.ahmedorabi.movieapp.repository.data.trailermodel.TrailerResponse;
import com.test.ahmedorabi.movieapp.repository.data.tvModel.TvModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ahmed Orabi on 7/9/2018.
 */

public interface ApiInterface {

     String TMDB_BASE_URL = "http://api.themoviedb.org/3/";


    // movies //////////
    @GET("movie/now_playing")
    LiveData<ApiResponse<MovieResponse>> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    LiveData<ApiResponse<MovieResponse>> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    LiveData<ApiResponse<MovieResponse>> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    LiveData<ApiResponse<MovieResponse>> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("discover/movie")
    LiveData<ApiResponse<MovieResponse>> TopHorrorMovies(@Query("api_key") String apiKey, @Query("language") String language,
                                        @Query("include_adult") String adult, @Query("include_video") String video, @Query("vote_average.gte") String vote1,
                                        @Query("vote_average.lte") String lte, @Query("with_genres") String genres

    );

    @GET("discover/movie")
    LiveData<ApiResponse<MovieResponse>> TopActionrMovies(@Query("api_key") String apiKey, @Query("language") String language,
                                         @Query("include_adult") String adult, @Query("include_video") String video, @Query("vote_average.gte") String vote1,
                                         @Query("vote_average.lte") String lte, @Query("with_genres") String genres

    );

    @GET("discover/movie")
    LiveData<ApiResponse<MovieResponse>> TopRomanceMovies(@Query("api_key") String apiKey, @Query("language") String language,
                                         @Query("include_adult") String adult, @Query("include_video") String video, @Query("vote_average.gte") String vote1,
                                         @Query("vote_average.lte") String lte, @Query("with_genres") String genres

    );
    //////////////////////////////////////////////////////////



    /// Reviews//////////////////////////////////
    @GET("movie/{id}/reviews")
    LiveData<ApiResponse<ReviewResponse>> getReviews(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{id}/reviews")
    LiveData<ApiResponse<ReviewResponse>> getTvReviews(@Path("id") int id, @Query("api_key") String apiKey);
    //////////////////////////////////////////////



    // Person/////////////////////////////////
    @GET("person/{id}/movie_credits")
    LiveData<ApiResponse<KnownResponse>> getKnownResponse(@Path("id") int id, @Query("api_key") String apiKey);


    @GET("person/{id}/images")
    LiveData<ApiResponse<ActorImages>> getActorImages(@Path("id") int id, @Query("api_key") String apiKey);


    @GET("person/{id}")
    LiveData<ApiResponse<PersonResponse>> getPerson(@Path("id") int id, @Query("api_key") String apiKey);


    @GET("person/{id}/tv_credits")
    LiveData<ApiResponse<KnownResponse>> getKnownResponseTv(@Path("id") int id, @Query("api_key") String apiKey);
    ///////////////////////////////////////////



    ///// Episode Detail//////////////////////////////////////////
    @GET("tv/{id}/season/{number}")
    LiveData<ApiResponse<SeasonResponse>> getSeasons(@Path("id") int id,@Path("number") int number,@Query("api_key") String apiKey);


    @GET("tv/{id}/season/{number}/episode/{episode}/images")
    LiveData<ApiResponse<ImagesResponse>> getImagesEpisode(@Path("id") int id,@Path("number") int number,@Path("episode") long episode
            ,@Query("api_key") String apiKey
    );
    /////////////////////////////////////////////////////////////////




    /// Detail Activity fragment/////////////////////////
    @GET("movie/{id}/videos")
    LiveData<ApiResponse<TrailerResponse>> getTrailers(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{id}/videos")
    LiveData<ApiResponse<TrailerResponse>> getTvTrailers(@Path("id") int id, @Query("api_key") String apiKey);


    @GET("movie/{id}/credits")
    LiveData<ApiResponse<CreditsResponse>> getCast(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{id}/credits")
    LiveData<ApiResponse<CreditsResponse>> getTvCast(@Path("id") int id, @Query("api_key") String apiKey);


    @GET("movie/{id}/images")
    LiveData<ApiResponse<BackdropsModel>> getAllBackdropss(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{id}/images")
    LiveData<ApiResponse<BackdropsModel>> getAllBackdropssTv(@Path("id") int id, @Query("api_key") String apiKey);


    @GET("movie/{id}/similar")
    LiveData<ApiResponse<SimilarResponse>> getsimilar(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{id}/similar")
    LiveData<ApiResponse<SimilarTvResponse>> getsimilarTv(@Path("id") int id, @Query("api_key") String apiKey);


    @GET("movie/{id}")
    LiveData<ApiResponse<GenresResponse>> getGenres(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{id}")
    LiveData<ApiResponse<TvGenresResponse>> getTvGenres(@Path("id") int id, @Query("api_key") String apiKey);


    @GET("tv/{id}/external_ids")
    LiveData<ApiResponse<IdResponse>> getIDS(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/external_ids")
    LiveData<ApiResponse<IdResponse>> getIDSMovies(@Path("id") int id, @Query("api_key") String apiKey);



    /////////////////////////////////////////////////////////////////////////////////////////


    // search
    @GET("search/multi")
    LiveData<ApiResponse<SearchResponse>> searchMovies(@Query("api_key") String apiKey, @Query("query") String query);

    ///////////////////////////////////////////////////////////////////////////////



    @GET("/")
    LiveData<ApiResponse<RatingResponse>> getRatings(@Query("i") String i, @Query("apikey") String key);

    @GET("/")
    LiveData<ApiResponse<TvRatingResponse>> getRatingsTV(@Query("i") String i, @Query("apikey") String key);




    // TV shows ///////////////////
    @GET("tv/top_rated")
    LiveData<ApiResponse<TvModel>> getTopRatedTVSHOWS(@Query("api_key") String apiKey);

    @GET("tv/popular")
    LiveData<ApiResponse<TvModel>> getPopularTVSHOWS(@Query("api_key") String apiKey);

    @GET("tv/airing_today")
    LiveData<ApiResponse<TvModel>> getAiringTodayTVSHOWS(@Query("api_key") String apiKey);

    @GET("tv/on_the_air")
    LiveData<ApiResponse<TvModel>> getOnTheAirTVSHOWS(@Query("api_key") String apiKey);


    @GET("discover/tv")
    LiveData<ApiResponse<TvModel>> DisoverTopTv(@Query("api_key") String apiKey, @Query("vote_average.gte") String gte, @Query("with_genres") String genres);

    //////////////////////////////////////////////







}
