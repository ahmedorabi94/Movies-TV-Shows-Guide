package com.test.ahmedorabi.movieapp.repository;

import androidx.lifecycle.LiveData;

import com.test.ahmedorabi.movieapp.repository.db.Movie;
import com.test.ahmedorabi.movieapp.repository.db.MovieDao;

import java.util.List;

import javax.inject.Inject;

public class FavouriteRepository {

    private LiveData<List<Movie>> allMovies;

    @Inject
    public FavouriteRepository(MovieDao movieDao) {
        allMovies = movieDao.getAllMovies();
    }

    public LiveData<List<Movie>> getAsLiveData() {
        return allMovies;
    }
}
