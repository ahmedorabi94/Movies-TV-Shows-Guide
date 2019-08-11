package com.test.ahmedorabi.movieapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;

import com.test.ahmedorabi.movieapp.model.MovieType;
import com.test.ahmedorabi.movieapp.model.api.Resource;
import com.test.ahmedorabi.movieapp.model.repository.ReviewService;
import com.test.ahmedorabi.movieapp.model.appModels.reviewModel.ReviewResponse;

import javax.inject.Inject;

public class ReviewViewModel extends AndroidViewModel {

    private static final MutableLiveData ABSENT = new MutableLiveData();

    static {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    private final LiveData<Resource<ReviewResponse>> reviewResponseLiveData;
    private final MutableLiveData<MovieType> movieType;


    @Inject
    public ReviewViewModel(ReviewService reviewService, @NonNull Application application) {
        super(application);

        this.movieType = new MutableLiveData<>();


        reviewResponseLiveData = Transformations.switchMap(movieType, input -> {
            if (input == null) {
                return ABSENT;
            }
            return reviewService.getReviews(input.getId(),input.getType());
        });


    }


    public LiveData<Resource<ReviewResponse>> getReviewResponseLiveData() {
        return reviewResponseLiveData;
    }


    public void setMovieType(MovieType type) {
        this.movieType.setValue(type);
    }

}
