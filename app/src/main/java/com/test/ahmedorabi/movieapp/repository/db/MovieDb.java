package com.test.ahmedorabi.movieapp.repository.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDb extends RoomDatabase {

    public abstract MovieDao movieDao();
}
