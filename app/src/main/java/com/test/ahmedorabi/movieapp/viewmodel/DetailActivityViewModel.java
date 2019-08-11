package com.test.ahmedorabi.movieapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;

import com.test.ahmedorabi.movieapp.model.MovieType;
import com.test.ahmedorabi.movieapp.model.api.Resource;
import com.test.ahmedorabi.movieapp.model.appModels.IDResponse.IdResponse;
import com.test.ahmedorabi.movieapp.model.appModels.RatingResponse.RatingResponse;
import com.test.ahmedorabi.movieapp.model.appModels.TvGenresResponse.TvGenresResponse;
import com.test.ahmedorabi.movieapp.model.appModels.TvRatingResponse.TvRatingResponse;
import com.test.ahmedorabi.movieapp.model.appModels.backdropsModel.BackdropsModel;
import com.test.ahmedorabi.movieapp.model.appModels.creditsModel.CreditsResponse;
import com.test.ahmedorabi.movieapp.model.appModels.genresmodel.GenresResponse;
import com.test.ahmedorabi.movieapp.model.appModels.reviewModel.Result;
import com.test.ahmedorabi.movieapp.model.appModels.reviewModel.ReviewResponse;
import com.test.ahmedorabi.movieapp.model.appModels.similar.SimilarResponse;
import com.test.ahmedorabi.movieapp.model.appModels.similarTvModel.SimilarTvResponse;
import com.test.ahmedorabi.movieapp.model.appModels.trailermodel.TrailerResponse;
import com.test.ahmedorabi.movieapp.model.dbroom.Movie;
import com.test.ahmedorabi.movieapp.model.repository.DetailActivityService;

import java.util.List;

import javax.inject.Inject;

public class DetailActivityViewModel extends AndroidViewModel {

    private static final MutableLiveData ABSENT = new MutableLiveData();

    static {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    public final MutableLiveData<MovieType> movieType;

    private final LiveData<Resource<SimilarResponse>> similarResponseLiveData;
    private final LiveData<Resource<SimilarTvResponse>> tvResponseLiveData;
    private final LiveData<Resource<GenresResponse>> moviesGenresObservable;
    private final LiveData<Resource<TvGenresResponse>> tvGenresObservable;
    private final LiveData<Resource<ReviewResponse>> reviewResponseLiveData;
    private final LiveData<Resource<BackdropsModel>> backdropsObservable;
    private final LiveData<Resource<CreditsResponse>> castObservable;
    private final LiveData<Resource<TrailerResponse>> trailerObservable;
    private final LiveData<Resource<IdResponse>> idsLiveData;

    private final LiveData<Resource<RatingResponse>> ratingResponseLiveData;
    private final LiveData<Resource<TvRatingResponse>> tvRatingResponseLiveData;

    private final MutableLiveData<String> mImdb;
    public ObservableField<Result> reveiwItem = new ObservableField<>();

    public LiveData<Resource<TvRatingResponse>> getTvRatingResponseLiveData() {
        return tvRatingResponseLiveData;
    }

    private DetailActivityService repo;
    private LiveData<List<Movie>> allMovies;


    @Inject
    public DetailActivityViewModel(DetailActivityService service, @NonNull Application application) {

        super(application);

        this.movieType = new MutableLiveData<>();
        this.mImdb = new MutableLiveData<>();
        this.repo = service;
        allMovies = service.getAsLiveData();

        tvRatingResponseLiveData = Transformations.switchMap(mImdb, input -> {
            if (input.isEmpty()) {
                return ABSENT;
            }
            return service.getRatingTv(input);
        });

        ratingResponseLiveData = Transformations.switchMap(mImdb, input -> {
            if (input.isEmpty()) {
                return ABSENT;
            }
            return service.getRatingsMovies(input);
        });


        idsLiveData = Transformations.switchMap(movieType, input -> {

            if (input == null) {
                return ABSENT;
            }
            return service.getIDS(input.getId(), input.getType());
        });


        similarResponseLiveData = Transformations.switchMap(movieType, input -> {
            if (input == null) {
                return ABSENT;
            }

            return service.getSimilarMovies(input.getId());

        });


        tvResponseLiveData = Transformations.switchMap(movieType, input -> {
            if (input == null) {
                return ABSENT;
            }
            return service.getSimilarTV(input.getId());
        });


        moviesGenresObservable = Transformations.switchMap(movieType, input -> {
            if (input == null) {
                return ABSENT;
            }
            return service.getMoviesGenres(input.getId());
        });


        tvGenresObservable = Transformations.switchMap(movieType, input -> {
            if (input == null) {
                return ABSENT;
            }
            return service.getTVGenres(input.getId());
        });


        reviewResponseLiveData = Transformations.switchMap(movieType, input -> {

            if (input == null) {
                return ABSENT;
            }

            return service.getReviews(input.getId(), input.getType());
        });


        backdropsObservable = Transformations.switchMap(movieType, input -> {
            if (input == null) {
                return ABSENT;
            }

            return service.getBackdrops(input.getId(), input.getType());

        });

        castObservable = Transformations.switchMap(movieType, input -> {
            if (input == null) {
                return ABSENT;
            }

            return service.getCast(input.getId(), input.getType());

        });

        trailerObservable = Transformations.switchMap(movieType, input -> {
            if (input == null) {
                return ABSENT;
            }

            return service.getTrailers(input.getId(), input.getType());

        });

    }

    public LiveData<Resource<RatingResponse>> getRatingResponseLiveData() {
        return ratingResponseLiveData;
    }

    public void setImdb(String imdb) {
        this.mImdb.setValue(imdb);
    }

    public LiveData<Resource<IdResponse>> getIdsLiveData() {
        return idsLiveData;
    }

    public void setReveiwItem(Result reveiwItem) {
        this.reveiwItem.set(reveiwItem);
    }

    public void setMovieType(MovieType type) {
        this.movieType.setValue(type);
    }


    public LiveData<Resource<SimilarResponse>> getSimilarResponseLiveData() {
        return similarResponseLiveData;
    }

    public LiveData<Resource<SimilarTvResponse>> getTvResponseLiveData() {
        return tvResponseLiveData;
    }


    public LiveData<Resource<GenresResponse>> getMoviesGenresObservable() {
        return moviesGenresObservable;
    }

    public LiveData<Resource<TvGenresResponse>> getTvGenresObservable() {
        return tvGenresObservable;
    }

    public LiveData<Resource<ReviewResponse>> getReviewResponseLiveData() {
        return reviewResponseLiveData;
    }

    public LiveData<Resource<BackdropsModel>> getBackdropsObservable() {
        return backdropsObservable;
    }

    public LiveData<Resource<CreditsResponse>> getCastObservable() {
        return castObservable;
    }

    public LiveData<Resource<TrailerResponse>> getTrailerObservable() {
        return trailerObservable;
    }


    public void insertMovie(Movie movie){
        repo.insertMovie(movie);
    }

    public void deleteMovie(String id){
        repo.deleteMovie(id);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }
}
