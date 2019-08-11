package com.test.ahmedorabi.movieapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;

import com.test.ahmedorabi.movieapp.repository.data.MovieType;
import com.test.ahmedorabi.movieapp.api.Resource;
import com.test.ahmedorabi.movieapp.repository.PosterRepository;
import com.test.ahmedorabi.movieapp.repository.data.ActorImages.ActorImages;
import com.test.ahmedorabi.movieapp.repository.data.backdropsModel.BackdropsModel;

import javax.inject.Inject;

public class PosterViewModel extends AndroidViewModel {

    private static final MutableLiveData ABSENT = new MutableLiveData();

    static {
        //noinspection unchecked
        ABSENT.setValue(null);
    }


    private final LiveData<Resource<ActorImages>> actorImages;
    private final LiveData<Resource<BackdropsModel>> backdrops;

    private final MutableLiveData<MovieType> movieType;


    @Inject
    public PosterViewModel(PosterRepository repository, @NonNull Application application) {
        super(application);

        this.movieType = new MutableLiveData<>();

        actorImages = Transformations.switchMap(movieType, input -> {

            if (input == null) {
                return ABSENT;
            }
            return repository.getPersonImages(input.getmActorId());
        });

        backdrops = Transformations.switchMap(movieType, input -> {
            if (input == null) {
                return ABSENT;
            }

            return repository.getBackdrop(input.getId(), input.getType());

        });


    }

    public void setMovieType(MovieType type) {
        this.movieType.setValue(type);
    }


    public LiveData<Resource<ActorImages>> getActorImages() {
        return actorImages;
    }

    public LiveData<Resource<BackdropsModel>> getBackdrops() {
        return backdrops;
    }
}
