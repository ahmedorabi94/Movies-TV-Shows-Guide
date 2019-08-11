package com.test.ahmedorabi.movieapp.model.repository;

import androidx.lifecycle.LiveData;

import com.test.ahmedorabi.movieapp.AppExecutors;
import com.test.ahmedorabi.movieapp.model.api.ApiResponse;
import com.test.ahmedorabi.movieapp.model.api.NetworkBoundResource;
import com.test.ahmedorabi.movieapp.model.api.Resource;
import com.test.ahmedorabi.movieapp.model.appModels.IDResponse.IdResponse;
import com.test.ahmedorabi.movieapp.model.appModels.RatingResponse.RatingResponse;
import com.test.ahmedorabi.movieapp.model.appModels.TvGenresResponse.TvGenresResponse;
import com.test.ahmedorabi.movieapp.model.appModels.TvRatingResponse.TvRatingResponse;
import com.test.ahmedorabi.movieapp.model.appModels.backdropsModel.BackdropsModel;
import com.test.ahmedorabi.movieapp.model.appModels.creditsModel.CreditsResponse;
import com.test.ahmedorabi.movieapp.model.appModels.genresmodel.GenresResponse;
import com.test.ahmedorabi.movieapp.model.appModels.reviewModel.ReviewResponse;
import com.test.ahmedorabi.movieapp.model.appModels.similar.SimilarResponse;
import com.test.ahmedorabi.movieapp.model.appModels.similarTvModel.SimilarTvResponse;
import com.test.ahmedorabi.movieapp.model.appModels.trailermodel.TrailerResponse;
import com.test.ahmedorabi.movieapp.model.dbroom.Movie;
import com.test.ahmedorabi.movieapp.model.dbroom.MovieDao;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;
import com.test.ahmedorabi.movieapp.view.ui.MoviesFragment;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class DetailActivityService {

    private ApiInterface mApiInterface;
    private ApiInterface mOmdbInterface;

    private MovieDao movieDao;
    private AppExecutors appExecutors;
    private LiveData<List<Movie>> allMovies;

    @Inject
    public DetailActivityService(@Named("tmdb") ApiInterface apiInterface, @Named("omdb") ApiInterface omdbInterface, MovieDao movieDao, AppExecutors appExecutors) {

        this.mApiInterface = apiInterface;
        this.mOmdbInterface = omdbInterface;
        this.movieDao = movieDao;
        this.appExecutors = appExecutors;
        allMovies = movieDao.getAllMovies();

    }


    public LiveData<Resource<TrailerResponse>> getTrailers(int id, String type) {

        return new NetworkBoundResource<TrailerResponse, TrailerResponse>() {
            @Override
            protected LiveData<ApiResponse<TrailerResponse>> createCall() {
                if (type.equals(MoviesFragment.MOVIE_VALUE)) {
                    return mApiInterface.getTrailers(id, MainActivity.API_KEY);
                } else {
                    return mApiInterface.getTvTrailers(id, MainActivity.API_KEY);
                }
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<CreditsResponse>> getCast(int id_movie, String type) {
        return new NetworkBoundResource<CreditsResponse, CreditsResponse>() {
            @Override
            protected LiveData<ApiResponse<CreditsResponse>> createCall() {
                if (type.equals(MoviesFragment.MOVIE_VALUE)) {
                    return mApiInterface.getCast(id_movie, MainActivity.API_KEY);
                } else {
                    return mApiInterface.getTvCast(id_movie, MainActivity.API_KEY);
                }
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<SimilarResponse>> getSimilarMovies(int id) {

        return new NetworkBoundResource<SimilarResponse, SimilarResponse>() {
            @Override
            protected LiveData<ApiResponse<SimilarResponse>> createCall() {
                return mApiInterface.getsimilar(id, MainActivity.API_KEY);
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<SimilarTvResponse>> getSimilarTV(int id) {

        return new NetworkBoundResource<SimilarTvResponse, SimilarTvResponse>() {
            @Override
            protected LiveData<ApiResponse<SimilarTvResponse>> createCall() {
                return mApiInterface.getsimilarTv(id, MainActivity.API_KEY);
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<BackdropsModel>> getBackdrops(int id_movie, String type) {

        return new NetworkBoundResource<BackdropsModel, BackdropsModel>() {

            @Override
            protected LiveData<ApiResponse<BackdropsModel>> createCall() {
                if (type.equals(MoviesFragment.MOVIE_VALUE)) {
                    return mApiInterface.getAllBackdropss(id_movie, MainActivity.API_KEY);
                } else {
                    return mApiInterface.getAllBackdropssTv(id_movie, MainActivity.API_KEY);
                }
            }
        }.getAsLiveData();

    }

    public LiveData<Resource<TvRatingResponse>> getRatingTv(String id) {

        return new NetworkBoundResource<TvRatingResponse, TvRatingResponse>() {

            @Override
            protected LiveData<ApiResponse<TvRatingResponse>> createCall() {
                return mOmdbInterface.getRatingsTV(id, "f1fee156");
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<RatingResponse>> getRatingsMovies(String id) {

        return new NetworkBoundResource<RatingResponse, RatingResponse>() {

            @Override
            protected LiveData<ApiResponse<RatingResponse>> createCall() {
                return mOmdbInterface.getRatings(id, "f1fee156");
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<ReviewResponse>> getReviews(int id_movie, String type) {


        return new NetworkBoundResource<ReviewResponse, ReviewResponse>() {

            @Override
            protected LiveData<ApiResponse<ReviewResponse>> createCall() {
                if (type.equals(MoviesFragment.MOVIE_VALUE)) {
                    return mApiInterface.getReviews(id_movie, MainActivity.API_KEY);

                } else {
                    return mApiInterface.getTvReviews(id_movie, MainActivity.API_KEY);
                }
            }
        }.getAsLiveData();


    }

    public LiveData<Resource<IdResponse>> getIDS(int id_movie, String type) {

        return new NetworkBoundResource<IdResponse, IdResponse>() {
            @Override
            protected LiveData<ApiResponse<IdResponse>> createCall() {
                if (type.equals(MoviesFragment.MOVIE_VALUE)) {
                    return mApiInterface.getIDSMovies(id_movie, MainActivity.API_KEY);

                } else {
                    return mApiInterface.getIDS(id_movie, MainActivity.API_KEY);
                }
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<GenresResponse>> getMoviesGenres(int id_movie) {
        return new NetworkBoundResource<GenresResponse, GenresResponse>() {
            @Override
            protected LiveData<ApiResponse<GenresResponse>> createCall() {
                return mApiInterface.getGenres(id_movie, MainActivity.API_KEY);
            }
        }.getAsLiveData();

    }

    public LiveData<Resource<TvGenresResponse>> getTVGenres(int id) {

        return new NetworkBoundResource<TvGenresResponse, TvGenresResponse>() {

            @Override
            protected LiveData<ApiResponse<TvGenresResponse>> createCall() {
                return mApiInterface.getTvGenres(id, MainActivity.API_KEY);
            }
        }.getAsLiveData();

    }


    public void insertMovie(Movie movie) {
        appExecutors.diskIO().execute(() -> movieDao.insert(movie));
    }

    public void deleteMovie(String id){
        appExecutors.diskIO().execute(() -> movieDao.deleteMovie(id));
    }


    public LiveData<List<Movie>> getAsLiveData(){
        return allMovies;
    }


}
