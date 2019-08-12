package com.test.ahmedorabi.movieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.test.ahmedorabi.movieapp.api.Resource;
import com.test.ahmedorabi.movieapp.repository.PersonRepository;
import com.test.ahmedorabi.movieapp.repository.data.knownResponse.KnownResponse;
import com.test.ahmedorabi.movieapp.repository.data.personModel.PersonResponse;
import com.test.ahmedorabi.movieapp.util.AbsentLiveData;

import javax.inject.Inject;

public class PersonViewModel extends AndroidViewModel {

    private final LiveData<Resource<PersonResponse>> personResponseLiveData;
    private final LiveData<Resource<KnownResponse>> knownResponseMoviesLiveData;
    private final LiveData<Resource<KnownResponse>> knownResponseTVLiveData;

    public ObservableField<PersonResponse> person = new ObservableField<>();

    private final MutableLiveData<String> ID;


    @Inject
    public PersonViewModel(PersonRepository repository, @NonNull Application application) {
        super(application);

        this.ID = new MutableLiveData<>();

        personResponseLiveData = Transformations.switchMap(ID, input -> {
            if (input.isEmpty()) {
                return AbsentLiveData.create();
            } else {
                return repository.getPerson(Integer.parseInt(input));
            }

        });


        knownResponseMoviesLiveData = Transformations.switchMap(ID, input -> {
            if (input.isEmpty()) {
                return AbsentLiveData.create();
            } else {
                return repository.getKnownMovies(Integer.parseInt(input));
            }

        });


        knownResponseTVLiveData = Transformations.switchMap(ID, input -> {
            if (input.isEmpty()) {
                return AbsentLiveData.create();
            } else {
                return repository.getKnownTV(Integer.parseInt(input));

            }
        });


    }

    public void setID(String id) {
        this.ID.setValue(id);
    }

    public void setPerson(PersonResponse person) {
        this.person.set(person);
    }

    public LiveData<Resource<KnownResponse>> getKnownResponseMoviesLiveData() {
        return knownResponseMoviesLiveData;
    }

    public LiveData<Resource<KnownResponse>> getKnownResponseTVLiveData() {
        return knownResponseTVLiveData;
    }

    public LiveData<Resource<PersonResponse>> getPersonResponseLiveData() {
        return personResponseLiveData;
    }


}
