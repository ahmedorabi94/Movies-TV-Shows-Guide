package com.test.ahmedorabi.movieapp.repository;

import androidx.lifecycle.LiveData;

import com.test.ahmedorabi.movieapp.api.ApiInterface;
import com.test.ahmedorabi.movieapp.api.ApiResponse;
import com.test.ahmedorabi.movieapp.api.NetworkBoundResource;
import com.test.ahmedorabi.movieapp.api.Resource;
import com.test.ahmedorabi.movieapp.repository.data.reviewModel.ReviewResponse;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;
import com.test.ahmedorabi.movieapp.view.ui.MoviesFragment;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class ReviewRepository {


    private ApiInterface mApiInterface;

    @Inject
    public ReviewRepository(@Named("tmdb") ApiInterface apiInterface) {
        this.mApiInterface = apiInterface;

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



}
