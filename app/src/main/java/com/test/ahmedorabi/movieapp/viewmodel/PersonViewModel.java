package com.test.ahmedorabi.movieapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;

import com.test.ahmedorabi.movieapp.model.api.Resource;
import com.test.ahmedorabi.movieapp.model.appModels.knownResponse.KnownResponse;
import com.test.ahmedorabi.movieapp.model.appModels.personModel.PersonResponse;
import com.test.ahmedorabi.movieapp.model.repository.PersonService;

import javax.inject.Inject;

public class PersonViewModel extends AndroidViewModel {

    private static final MutableLiveData ABSENT = new MutableLiveData();

    static {
        //noinspection unchecked
        ABSENT.setValue(null);
    }


    private final LiveData<Resource<PersonResponse>> personResponseLiveData;
    private final LiveData<Resource<KnownResponse>> knownResponseMoviesLiveData;
    private final LiveData<Resource<KnownResponse>> knownResponseTVLiveData;

    public ObservableField<PersonResponse> person = new ObservableField<>();

    private final MutableLiveData<String> ID;


    @Inject
    public PersonViewModel(PersonService personService, @NonNull Application application) {
        super(application);

        this.ID = new MutableLiveData<>();

        personResponseLiveData = Transformations.switchMap(ID, input -> {
            if (input.isEmpty()) {
                return ABSENT;
            }

            return personService.getPerson(Integer.parseInt(input));
        });


        knownResponseMoviesLiveData = Transformations.switchMap(ID, input -> {
            if (input.isEmpty()) {
                return ABSENT;
            }

            return personService.getKnownMovies(Integer.parseInt(input));
        });


        knownResponseTVLiveData = Transformations.switchMap(ID, input -> {
            if (input.isEmpty()) {
                return ABSENT;
            }

            return personService.getKnownTV(Integer.parseInt(input));
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
