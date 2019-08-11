package com.test.ahmedorabi.movieapp.model.repository;

import androidx.lifecycle.LiveData;

import com.test.ahmedorabi.movieapp.model.api.ApiResponse;
import com.test.ahmedorabi.movieapp.model.api.NetworkBoundResource;
import com.test.ahmedorabi.movieapp.model.api.Resource;
import com.test.ahmedorabi.movieapp.model.appModels.ActorImages.ActorImages;
import com.test.ahmedorabi.movieapp.model.appModels.backdropsModel.BackdropsModel;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;
import com.test.ahmedorabi.movieapp.view.ui.MoviesFragment;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class PosterService {

    private ApiInterface mApiInterface;

    @Inject
    public PosterService(@Named("tmdb") ApiInterface apiInterface) {
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
