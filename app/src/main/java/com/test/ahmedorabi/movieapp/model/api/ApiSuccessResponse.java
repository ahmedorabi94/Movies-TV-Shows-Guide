package com.test.ahmedorabi.movieapp.model.api;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class ApiSuccessResponse<T> extends ApiResponse<T> {


    private final T body;


    public ApiSuccessResponse(T body) {
        this.body = body;
    }

    public static ApiSuccessResponse copy$default(ApiSuccessResponse var0, Object var1) {


        return var0.copy(var1);
    }

    public final T getBody() {
        return this.body;
    }

    public final ApiSuccessResponse copy(Object body) {
        return new ApiSuccessResponse(body);
    }

    @NonNull
    public String toString() {
        return "ApiSuccessResponse(body=" + this.body + ")";
    }

    public int hashCode() {
        return (this.body != null ? this.body.hashCode() : 0);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof ApiSuccessResponse) {
                ApiSuccessResponse var2 = (ApiSuccessResponse) var1;


                return this.body.equals(var2.body);
            }

            return false;
        } else {
            return true;
        }
    }

}
