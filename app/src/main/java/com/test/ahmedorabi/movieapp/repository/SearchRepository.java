package com.test.ahmedorabi.movieapp.repository;

import androidx.lifecycle.LiveData;

import com.test.ahmedorabi.movieapp.api.ApiInterface;
import com.test.ahmedorabi.movieapp.api.ApiResponse;
import com.test.ahmedorabi.movieapp.api.NetworkBoundResource;
import com.test.ahmedorabi.movieapp.api.Resource;
import com.test.ahmedorabi.movieapp.repository.data.searchModel.SearchResponse;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class SearchRepository {

    private ApiInterface mApiInterface;


    @Inject
    public SearchRepository(@Named("tmdb") ApiInterface apiInterface) {
        this.mApiInterface = apiInterface;
    }


    public LiveData<Resource<SearchResponse>> getSearchResults(String query) {
        return new NetworkBoundResource<SearchResponse, SearchResponse>() {
            @Override
            protected LiveData<ApiResponse<SearchResponse>> createCall() {
                return mApiInterface.searchMovies(MainActivity.API_KEY, query);
            }
        }.getAsLiveData();

    }


}
