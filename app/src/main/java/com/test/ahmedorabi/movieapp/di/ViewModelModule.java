package com.test.ahmedorabi.movieapp.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.test.ahmedorabi.movieapp.viewmodel.DetailActivityViewModel;
import com.test.ahmedorabi.movieapp.viewmodel.EpisodeDetailViewModel;
import com.test.ahmedorabi.movieapp.viewmodel.FavouriteViewModel;
import com.test.ahmedorabi.movieapp.viewmodel.MovieViewModelFactory;
import com.test.ahmedorabi.movieapp.viewmodel.MoviesFragmentViewModel;
import com.test.ahmedorabi.movieapp.viewmodel.PersonViewModel;
import com.test.ahmedorabi.movieapp.viewmodel.PosterViewModel;
import com.test.ahmedorabi.movieapp.viewmodel.ReviewViewModel;
import com.test.ahmedorabi.movieapp.viewmodel.SearchViewModel;
import com.test.ahmedorabi.movieapp.viewmodel.TVFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MoviesFragmentViewModel.class)
    abstract ViewModel bindMoviesViewModel(MoviesFragmentViewModel moviesFragmentViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(TVFragmentViewModel.class)
    abstract ViewModel bindTVViewModel(TVFragmentViewModel tvFragmentViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(ReviewViewModel.class)
    abstract ViewModel bindReviewViewModel(ReviewViewModel reviewViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(PersonViewModel.class)
    abstract ViewModel bindPersonViewModel(PersonViewModel reviewViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(EpisodeDetailViewModel.class)
    abstract ViewModel bindEpisodeViewModel(EpisodeDetailViewModel episodeDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PosterViewModel.class)
    abstract ViewModel bindPosterViewModel(PosterViewModel posterViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel bindSearchViewModel(SearchViewModel searchViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel.class)
    abstract ViewModel bindDetailViewModel(DetailActivityViewModel detailActivityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel.class)
    abstract ViewModel bindFavouriteViewModel(FavouriteViewModel favouriteViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MovieViewModelFactory factory);


}
