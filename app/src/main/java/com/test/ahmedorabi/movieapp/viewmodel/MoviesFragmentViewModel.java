package com.test.ahmedorabi.movieapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.test.ahmedorabi.movieapp.api.Resource;
import com.test.ahmedorabi.movieapp.repository.MoviesFragmentRepository;
import com.test.ahmedorabi.movieapp.repository.data.moviemodel.MovieResponse;

import javax.inject.Inject;

public class MoviesFragmentViewModel extends AndroidViewModel {

    private final LiveData<Resource<MovieResponse>> NowPlayingObservable;
    private final LiveData<Resource<MovieResponse>> TopRatedObservable;
    private final LiveData<Resource<MovieResponse>> UpcomingObservable;
    private final LiveData<Resource<MovieResponse>> PopularObservable;
    private final LiveData<Resource<MovieResponse>> TopHorroObservable;
    private final LiveData<Resource<MovieResponse>> TopActionObservable;
    private final LiveData<Resource<MovieResponse>> TopRomanceObservable;






    @Inject
    public MoviesFragmentViewModel(MoviesFragmentRepository repository , @NonNull Application application) {
        super(application);

        NowPlayingObservable = repository.getNowPlaying();
        TopRatedObservable = repository.getTopRatedMovies();
        UpcomingObservable = repository.getUpcomingMovies();
        PopularObservable = repository.getpopularMovies();
        TopHorroObservable = repository.getTopRatedHorrorMovies();
        TopActionObservable = repository.getTopRatedActionMovies();
        TopRomanceObservable = repository.getTopRatedRomanceMovies();
    }


    public LiveData<Resource<MovieResponse>> getNowPlayingObservable() {
        return NowPlayingObservable;
    }

    public LiveData<Resource<MovieResponse>> getTopRatedObservable() {
        return TopRatedObservable;
    }

    public LiveData<Resource<MovieResponse>> getUpcomingObservable() {
        return UpcomingObservable;
    }

    public LiveData<Resource<MovieResponse>> getPopularObservable() {
        return PopularObservable;
    }



    public LiveData<Resource<MovieResponse>> getTopHorroObservable() {
        return TopHorroObservable;
    }

    public LiveData<Resource<MovieResponse>> getTopActionObservable() {
        return TopActionObservable;
    }

    public LiveData<Resource<MovieResponse>> getTopRomanceObservable() {
        return TopRomanceObservable;
    }


}
