package com.test.ahmedorabi.movieapp.di;

import com.test.ahmedorabi.movieapp.view.ui.FavouriteFragment;
import com.test.ahmedorabi.movieapp.view.ui.MainActivityFrgment;
import com.test.ahmedorabi.movieapp.view.ui.MoviesFragment;
import com.test.ahmedorabi.movieapp.view.ui.TVShowFragemnt;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public  abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract MainActivityFrgment contributeMainActivityFragment();

    @ContributesAndroidInjector
    abstract MoviesFragment contributeMoviesFragment();

    @ContributesAndroidInjector
    abstract TVShowFragemnt contributeTVFragment();



}
