package com.test.ahmedorabi.movieapp.repository.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);


    @Query("SELECT * from movie")
    LiveData<List<Movie>> getAllMovies();


    @Query("DELETE FROM movie WHERE movie_id = :id")
    void deleteMovie(String id);


    @Query("SELECT * FROM MOVIE WHERE movie_id = :id")
    Movie getMovie(String id);


}
