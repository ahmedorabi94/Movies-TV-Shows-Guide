package com.test.ahmedorabi.movieapp.model.repository;

import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.test.ahmedorabi.movieapp.model.api.ApiResponse;
import com.test.ahmedorabi.movieapp.model.api.NetworkBoundResource;
import com.test.ahmedorabi.movieapp.model.api.Resource;
import com.test.ahmedorabi.movieapp.model.appModels.tvModel.TvModel;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class TVFragmentService {

    private ApiInterface mApiInterface;

    @Inject
    public TVFragmentService(@Named("tmdb") ApiInterface apiInterface) {
        this.mApiInterface = apiInterface;

    }


    public LiveData<Resource<TvModel>> getTopRatedTv() {

        return new NetworkBoundResource<TvModel, TvModel>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<TvModel>> createCall() {
                return mApiInterface.getTopRatedTVSHOWS(MainActivity.API_KEY);
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<TvModel>> getPopularTv() {

        return new NetworkBoundResource<TvModel, TvModel>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<TvModel>> createCall() {
                return mApiInterface.getPopularTVSHOWS(MainActivity.API_KEY);
            }
        }.getAsLiveData();


    }


    public LiveData<Resource<TvModel>> getOnTheAirTv() {

        return new NetworkBoundResource<TvModel, TvModel>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<TvModel>> createCall() {
                return mApiInterface.getOnTheAirTVSHOWS(MainActivity.API_KEY);
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<TvModel>> getAiringTodayTv() {

        return new NetworkBoundResource<TvModel, TvModel>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<TvModel>> createCall() {
                return mApiInterface.getAiringTodayTVSHOWS(MainActivity.API_KEY);
            }
        }.getAsLiveData();

    }

    public LiveData<Resource<TvModel>> getTopActionTv() {

        return new NetworkBoundResource<TvModel, TvModel>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<TvModel>> createCall() {
                return mApiInterface.DisoverTopTv(MainActivity.API_KEY, "7.2", "10759");
            }
        }.getAsLiveData();


    }

    public LiveData<Resource<TvModel>> getTopCrimeTv() {

        return new NetworkBoundResource<TvModel, TvModel>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<TvModel>> createCall() {
                return mApiInterface.DisoverTopTv(MainActivity.API_KEY, "8", "18,80");
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<TvModel>> getTopComedyTv() {

        return new NetworkBoundResource<TvModel, TvModel>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<TvModel>> createCall() {
                return mApiInterface.DisoverTopTv(MainActivity.API_KEY, "7.8", "35");
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<TvModel>> getTopWarTv() {

        return new NetworkBoundResource<TvModel, TvModel>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<TvModel>> createCall() {
                return mApiInterface.DisoverTopTv(MainActivity.API_KEY, "7.8", "10768");
            }
        }.getAsLiveData();

    }


}
