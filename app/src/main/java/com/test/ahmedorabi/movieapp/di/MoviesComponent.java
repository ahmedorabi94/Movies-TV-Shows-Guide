package com.test.ahmedorabi.movieapp.di;


import android.app.Application;

import com.test.ahmedorabi.movieapp.MoviesApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {MovieNetModule.class, AndroidInjectionModule.class, MainActivityModule.class})
public interface MoviesComponent {


    void inject(MoviesApplication moviesApplication);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);


        MoviesComponent build();
    }

}
