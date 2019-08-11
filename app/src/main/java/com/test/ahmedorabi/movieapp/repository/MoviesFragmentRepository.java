package com.test.ahmedorabi.movieapp.repository;

import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.test.ahmedorabi.movieapp.api.ApiInterface;
import com.test.ahmedorabi.movieapp.api.ApiResponse;
import com.test.ahmedorabi.movieapp.api.NetworkBoundResource;
import com.test.ahmedorabi.movieapp.api.Resource;
import com.test.ahmedorabi.movieapp.repository.data.moviemodel.MovieResponse;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class MoviesFragmentRepository {

    private ApiInterface mApiInterface;

    @Inject
    public MoviesFragmentRepository(@Named("tmdb") ApiInterface apiInterface) {
        this.mApiInterface = apiInterface;

    }


    public LiveData<Resource<MovieResponse>> getNowPlaying() {

        return new NetworkBoundResource<MovieResponse, MovieResponse>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return mApiInterface.getNowPlayingMovies(MainActivity.API_KEY);
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<MovieResponse>> getTopRatedMovies() {

        return new NetworkBoundResource<MovieResponse, MovieResponse>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return mApiInterface.getTopRatedMovies(MainActivity.API_KEY);

            }

        }.getAsLiveData();

    }

    public LiveData<Resource<MovieResponse>> getUpcomingMovies() {
        return new NetworkBoundResource<MovieResponse, MovieResponse>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return mApiInterface.getUpcomingMovies(MainActivity.API_KEY);

            }

        }.getAsLiveData();

    }

    public LiveData<Resource<MovieResponse>> getpopularMovies() {

        return new NetworkBoundResource<MovieResponse, MovieResponse>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return mApiInterface.getPopularMovies(MainActivity.API_KEY);

            }

        }.getAsLiveData();

    }

    public LiveData<Resource<MovieResponse>> getTopRatedHorrorMovies() {

        return new NetworkBoundResource<MovieResponse, MovieResponse>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return mApiInterface.TopHorrorMovies(MainActivity.API_KEY, "en", "true", "true", "7", "8", "27");

            }

        }.getAsLiveData();

    }


    public LiveData<Resource<MovieResponse>> getTopRatedActionMovies() {

        return new NetworkBoundResource<MovieResponse, MovieResponse>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return mApiInterface.TopActionrMovies(MainActivity.API_KEY, "en", "true", "true", "7", "8", "28,12");

            }

        }.getAsLiveData();

    }


    public LiveData<Resource<MovieResponse>> getTopRatedRomanceMovies() {

        return new NetworkBoundResource<MovieResponse, MovieResponse>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return mApiInterface.TopRomanceMovies(MainActivity.API_KEY, "en", "true", "true", "7.2", "8", "35,18,10749");

            }

        }.getAsLiveData();
    }


}
