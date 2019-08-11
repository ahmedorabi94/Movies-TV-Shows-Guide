package com.test.ahmedorabi.movieapp.repository;

import androidx.lifecycle.LiveData;

import com.test.ahmedorabi.movieapp.api.ApiInterface;
import com.test.ahmedorabi.movieapp.api.ApiResponse;
import com.test.ahmedorabi.movieapp.api.NetworkBoundResource;
import com.test.ahmedorabi.movieapp.api.Resource;
import com.test.ahmedorabi.movieapp.repository.data.ActorImages.ActorImages;
import com.test.ahmedorabi.movieapp.repository.data.backdropsModel.BackdropsModel;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;
import com.test.ahmedorabi.movieapp.view.ui.MoviesFragment;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class PosterRepository {

    private ApiInterface mApiInterface;

    @Inject
    public PosterRepository(@Named("tmdb") ApiInterface apiInterface) {
        this.mApiInterface = apiInterface;
    }


    public LiveData<Resource<ActorImages>> getPersonImages(int id) {
        return new NetworkBoundResource<ActorImages, ActorImages>() {
            @Override
            protected LiveData<ApiResponse<ActorImages>> createCall() {
                return mApiInterface.getActorImages(id, MainActivity.API_KEY);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<BackdropsModel>> getBackdrop(int id_movie, String type) {
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


}
