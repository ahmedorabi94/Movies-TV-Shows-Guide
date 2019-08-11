package com.test.ahmedorabi.movieapp.util;

import androidx.lifecycle.LiveData;

import com.test.ahmedorabi.movieapp.api.ApiResponse;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<R> implements CallAdapter<R,LiveData<ApiResponse<R>>> {

    private final Type mResponseType;



    public LiveDataCallAdapter(final Type responseType){
        this.mResponseType = responseType;

    }


    @Override
    public Type responseType() {
        return mResponseType;
    }



    @Override
    public LiveData<ApiResponse<R>> adapt(Call<R> call) {
        return new LiveData<ApiResponse<R>>() {

            private AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();

                if (started.compareAndSet(false,true)){
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {
                            postValue(new ApiResponse<R>(response));
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable t) {
                            postValue(new ApiResponse<R>(t));

                        }
                    });
                }


            }
        };
    }
}
