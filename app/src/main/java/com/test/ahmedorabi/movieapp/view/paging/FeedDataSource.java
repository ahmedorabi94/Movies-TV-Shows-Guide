package com.test.ahmedorabi.movieapp.view.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PageKeyedDataSource;

import com.test.ahmedorabi.movieapp.api.ApiInterface;
import com.test.ahmedorabi.movieapp.api.ApiResponse;
import com.test.ahmedorabi.movieapp.repository.data.TvGenresResponse.Network;
import com.test.ahmedorabi.movieapp.repository.data.moviemodel.MovieResponse;
import com.test.ahmedorabi.movieapp.util.NetworkState;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;

import javax.inject.Inject;

public class FeedDataSource extends PageKeyedDataSource<Long, MovieResponse> {


    private ApiInterface mApiInterface;
    private MutableLiveData networkState;
    private MutableLiveData initialLoading;


    @Inject
    public FeedDataSource(ApiInterface apiInterface) {
        this.mApiInterface = apiInterface;
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
    }

    /*
     * Step 2: This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, MovieResponse> callback) {

        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);




    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, MovieResponse> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, MovieResponse> callback) {

    }


    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }
}
