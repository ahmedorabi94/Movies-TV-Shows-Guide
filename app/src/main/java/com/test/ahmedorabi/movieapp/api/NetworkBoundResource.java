package com.test.ahmedorabi.movieapp.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.annotation.MainThread;


public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();


    @MainThread
    public NetworkBoundResource() {

        fetchFromNetwork();

    }


    private void fetchFromNetwork() {

        final LiveData<ApiResponse<RequestType>> apiResponse = createCall();


        result.addSource(apiResponse, response -> {

            if (response instanceof ApiSuccessResponse) {
                setValue(Resource.success(((ApiSuccessResponse<ResultType>) response).getBody()));
            }

            if (response instanceof ApiErrorResponse) {
                onFetchFailed();
                setValue(Resource.error(((ApiErrorResponse<ResultType>) response).getErrorMessage(), null));
            }


        });


    }


    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        if (result.getValue() != newValue) {
            result.setValue(newValue);
        }
    }


    // Called to create the API call.
    protected abstract LiveData<ApiResponse<RequestType>> createCall();


    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    @MainThread
    protected void onFetchFailed() {

    }


    // Returns a LiveData object that represents the resource that's implemented
    // in the base class.
    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return this.result;
    }

}



