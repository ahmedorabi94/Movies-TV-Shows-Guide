package com.test.ahmedorabi.movieapp.model.api;


import androidx.annotation.Nullable;

public final class ApiErrorResponse<T> extends ApiResponse<T> {

    private final String errorMessage;

    public ApiErrorResponse( String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static ApiErrorResponse copy$default(ApiErrorResponse var0, String var1, int var2, Object var3) {
        if ((var2 & 1) != 0) {
            var1 = var0.errorMessage;
        }

        return var0.copy(var1);
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final String component1() {
        return this.errorMessage;
    }

    public final ApiErrorResponse copy( String errorMessage) {
        return new ApiErrorResponse(errorMessage);
    }

    public String toString() {
        return "ApiErrorResponse(errorMessage=" + this.errorMessage + ")";
    }

    public int hashCode() {
        return this.errorMessage.hashCode();
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof ApiErrorResponse) {
                ApiErrorResponse var2 = (ApiErrorResponse) var1;

                return this.errorMessage.equals(var2.errorMessage);

            }

            return false;
        } else {
            return true;
        }
    }
}
