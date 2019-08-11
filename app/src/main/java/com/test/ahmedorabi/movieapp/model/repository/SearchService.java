package com.test.ahmedorabi.movieapp.model.repository;

import androidx.lifecycle.LiveData;

import com.test.ahmedorabi.movieapp.model.api.ApiResponse;
import com.test.ahmedorabi.movieapp.model.api.NetworkBoundResource;
import com.test.ahmedorabi.movieapp.model.api.Resource;
import com.test.ahmedorabi.movieapp.model.appModels.searchModel.SearchResponse;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class SearchService {

    private ApiInterface mApiInterface;


    @Inject
    public SearchService(@Named("tmdb") ApiInterface apiInterface) {
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
