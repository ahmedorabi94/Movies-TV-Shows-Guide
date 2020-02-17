package com.test.ahmedorabi.movieapp.api;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.test.ahmedorabi.movieapp.repository.data.similarTvModel.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();


    @MainThread
    public NetworkBoundResource() {

        fetchFromNetwork();

    }


    private void fetchFromNetwork() {

      final LiveData<ApiResponse<RequestType>> apiResponse = createCall();


        result.addSource(apiResponse, response -> {

            if (response.isSuccessful()){
                setValue(Resource.success(((ApiResponse<ResultType>)response).body));

            }else {
                setValue(Resource.error(response.errorMessage,null));
            }


        });


//        createCall().enqueue(new Callback<ApiResponse<RequestType>>() {
//            @Override
//            public void onResponse(Call<ApiResponse<RequestType>> call, Response<ApiResponse<RequestType>> response) {
//
//                if (response.isSuccessful()) {
//
//                    setValue(Resource.success(response.body().body));
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse<RequestType>> call, Throwable t) {
//                setValue(Resource.error(t.getMessage(), null));
//            }
//        });


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



