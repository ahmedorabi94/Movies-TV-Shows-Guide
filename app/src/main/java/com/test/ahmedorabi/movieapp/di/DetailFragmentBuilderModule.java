package com.test.ahmedorabi.movieapp.di;

import com.test.ahmedorabi.movieapp.view.ui.DetailActivityFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DetailFragmentBuilderModule {


    @ContributesAndroidInjector
    abstract DetailActivityFragment contributeDetailActivityFragment();
}
