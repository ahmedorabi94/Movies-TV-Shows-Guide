package com.test.ahmedorabi.movieapp.repository;

import androidx.lifecycle.LiveData;

import com.test.ahmedorabi.movieapp.api.ApiInterface;
import com.test.ahmedorabi.movieapp.api.ApiResponse;
import com.test.ahmedorabi.movieapp.api.NetworkBoundResource;
import com.test.ahmedorabi.movieapp.api.Resource;
import com.test.ahmedorabi.movieapp.repository.data.knownResponse.KnownResponse;
import com.test.ahmedorabi.movieapp.repository.data.personModel.PersonResponse;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class PersonRepository {


    private ApiInterface mApiInterface;

    @Inject
    public PersonRepository(@Named("tmdb") ApiInterface apiInterface) {
        this.mApiInterface = apiInterface;

    }


    public LiveData<Resource<PersonResponse>> getPerson(int id) {
        return new NetworkBoundResource<PersonResponse, PersonResponse>() {
            @Override
            protected LiveData<ApiResponse<PersonResponse>> createCall() {
                return mApiInterface.getPerson(id, MainActivity.API_KEY);
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<KnownResponse>> getKnownMovies(int id) {
        return new NetworkBoundResource<KnownResponse, KnownResponse>() {

            @Override
            protected LiveData<ApiResponse<KnownResponse>> createCall() {
                return mApiInterface.getKnownResponse(id, MainActivity.API_KEY);
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<KnownResponse>> getKnownTV(int id) {
        return new NetworkBoundResource<KnownResponse, KnownResponse>() {

            @Override
            protected LiveData<ApiResponse<KnownResponse>> createCall() {
                return mApiInterface.getKnownResponseTv(id, MainActivity.API_KEY);
            }
        }.getAsLiveData();

    }

}
