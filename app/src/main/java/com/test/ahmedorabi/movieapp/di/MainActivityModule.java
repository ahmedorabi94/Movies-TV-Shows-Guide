package com.test.ahmedorabi.movieapp.di;

import com.test.ahmedorabi.movieapp.view.ui.ActorDetailActivity;
import com.test.ahmedorabi.movieapp.view.ui.DetailActivity;
import com.test.ahmedorabi.movieapp.view.ui.DisplayPosterActivity;
import com.test.ahmedorabi.movieapp.view.ui.EpisodeDetailActivity;
import com.test.ahmedorabi.movieapp.view.ui.FavDetailActivity;
import com.test.ahmedorabi.movieapp.view.ui.FavouriteActivity;
import com.test.ahmedorabi.movieapp.view.ui.MainActivity;
import com.test.ahmedorabi.movieapp.view.ui.ReveiwActivity;
import com.test.ahmedorabi.movieapp.view.ui.SearchResultsActivity;
import com.test.ahmedorabi.movieapp.view.ui.SeasonDeatail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {


    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = DetailFragmentBuilderModule.class)
    abstract DetailActivity contributeDetailActivity();


    @ContributesAndroidInjector(modules = FavDetailFragmentModule.class)
    abstract FavDetailActivity contributeFavDetailActivity();

    @ContributesAndroidInjector(modules = FavouriteFragmentBuilder.class)
    abstract FavouriteActivity contributeFavDetaiFavouriteActivity();


    @ContributesAndroidInjector
    abstract ActorDetailActivity contributeActorActivity();

    @ContributesAndroidInjector
    abstract ReveiwActivity contributeReviewActivity();

    @ContributesAndroidInjector
    abstract DisplayPosterActivity displayPosterActivity();

    @ContributesAndroidInjector
    abstract SeasonDeatail contributeSeasonDetailActivity();

    @ContributesAndroidInjector
    abstract SearchResultsActivity searchResultsActivity();


    @ContributesAndroidInjector
    abstract EpisodeDetailActivity contributeEpisodeDetailActivity();

}
