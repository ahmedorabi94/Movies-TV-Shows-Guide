package com.test.ahmedorabi.movieapp.di;

import com.test.ahmedorabi.movieapp.view.ui.FavouriteDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FavDetailFragmentModule {

    @ContributesAndroidInjector
    abstract FavouriteDetailFragment contributeFavouriteDetailFragment();
}
