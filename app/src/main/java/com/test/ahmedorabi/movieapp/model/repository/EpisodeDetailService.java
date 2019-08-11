package com.test.ahmedorabi.movieapp.model.repository;

import androidx.lifecycle.LiveData;

import com.test.ahmedorabi.movieapp.model.api.ApiResponse;
import com.test.ahmedorabi.movieapp.model.api.NetworkBoundResource;
import com.test.ahmedorabi.movieapp.model.api.Resource;
import com.test.ahmedorabi.movieapp.model.appModels.imagesResponse.ImagesResponse;
import com.test.ahmedorabi.movieapp.model.appModels.seasonResponse.SeasonResponse;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class EpisodeDetailService {

    private ApiInterface mApiInterface;

    @Inject
    public EpisodeDetailService(@Named("tmdb") ApiInterface apiInterface) {

        this.mApiInterface = apiInterface;

    }


    public LiveData<Resource<ImagesResponse>> getImages(int id, int Snumber, long episode) {

        return new NetworkBoundResource<ImagesResponse, ImagesResponse>() {

            @Override
            protected LiveData<ApiResponse<ImagesResponse>> createCall() {
                return mApiInterface.getImagesEpisode(id, Snumber, episode, MainActivity.API_KEY);
            }
        }.getAsLiveData();


    }

    public LiveData<Resource<SeasonResponse>> getSeason(int idMovie, final int number) {

        return new NetworkBoundResource<SeasonResponse,SeasonResponse>(){

            @Override
            protected LiveData<ApiResponse<SeasonResponse>> createCall() {
                return mApiInterface.getSeasons(idMovie, number, MainActivity.API_KEY);
            }
        }.getAsLiveData();


    }


}
