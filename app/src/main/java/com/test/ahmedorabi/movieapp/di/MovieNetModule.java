package com.test.ahmedorabi.movieapp.di;


import android.app.Application;

import androidx.room.Room;

import com.test.ahmedorabi.movieapp.repository.db.MovieDao;
import com.test.ahmedorabi.movieapp.repository.db.MovieDb;
import com.test.ahmedorabi.movieapp.api.ApiInterface;
import com.test.ahmedorabi.movieapp.util.LiveDataCallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class MovieNetModule {


    @Named("tmdb")
    @Singleton
    @Provides
    ApiInterface provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ApiInterface.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(ApiInterface.class);

    }


    @Named("omdb")
    @Singleton
    @Provides
    ApiInterface provideOmdbRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(ApiInterface.class);

    }


    @Singleton
    @Provides
    MovieDb provideDb(Application application) {
        return Room.databaseBuilder(application, MovieDb.class, "movie_tv.db").build();
    }


    @Singleton
    @Provides
    MovieDao provideMovieDao(MovieDb movieDb) {
        return movieDb.movieDao();
    }


}
