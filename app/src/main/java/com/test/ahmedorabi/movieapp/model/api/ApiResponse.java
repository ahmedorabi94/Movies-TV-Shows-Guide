package com.test.ahmedorabi.movieapp.model.api;


import okhttp3.ResponseBody;
import retrofit2.Response;

public abstract class ApiResponse<T> {

    public static ApiErrorResponse create( Throwable error) {

        String errorMsg = error.getMessage();

        if (errorMsg == null) {
            errorMsg = "unknown error";
        }

        return new ApiErrorResponse(errorMsg);
    }

    public static  ApiResponse create(Response response) {

        if (response.isSuccessful()) {

            if (response.body() == null || response.code() == 204) {
                return new ApiEmptyResponse();
            } else {

                return new ApiSuccessResponse(response.body());
            }


        } else {

            ResponseBody body = response.errorBody();

            String msg = body != null ? body.toString() : null;

            String errorMsg = msg == null || msg.length() == 0 ? response.message() : msg;

            if (errorMsg == null) {
                errorMsg = "unknown error";
            }

            return new ApiErrorResponse(errorMsg);

        }


    }


}



