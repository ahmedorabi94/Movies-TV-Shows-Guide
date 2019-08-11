package com.test.ahmedorabi.movieapp.di;

import com.test.ahmedorabi.movieapp.view.ui.FavouriteFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FavouriteFragmentBuilder {


    @ContributesAndroidInjector
    abstract FavouriteFragment contributeFavouriteFragment();
}
