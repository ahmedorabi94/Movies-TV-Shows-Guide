package com.test.ahmedorabi.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.test.ahmedorabi.movieapp.model.dbroom.Movie;
import com.test.ahmedorabi.movieapp.model.repository.FavouriteRepository;

import java.util.List;

import javax.inject.Inject;

public class FavouriteViewModel extends ViewModel {


    private LiveData<List<Movie>> allMovies;

    @Inject
    public FavouriteViewModel(FavouriteRepository repository){
        allMovies = repository.getAsLiveData();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }


}
