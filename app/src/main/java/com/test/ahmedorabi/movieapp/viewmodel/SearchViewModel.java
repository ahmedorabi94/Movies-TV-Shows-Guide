package com.test.ahmedorabi.movieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.test.ahmedorabi.movieapp.api.Resource;
import com.test.ahmedorabi.movieapp.repository.SearchRepository;
import com.test.ahmedorabi.movieapp.repository.data.searchModel.SearchResponse;
import com.test.ahmedorabi.movieapp.util.AbsentLiveData;

import javax.inject.Inject;

public class SearchViewModel extends AndroidViewModel {

    private final LiveData<Resource<SearchResponse>> searchLiveData;
    private final MutableLiveData<String> query;


    @Inject
    public SearchViewModel(SearchRepository repository, @NonNull Application application) {
        super(application);

        this.query = new MutableLiveData<>();

        searchLiveData = Transformations.switchMap(query, input -> {

            if (input == null) {
                return AbsentLiveData.create();
            } else {
                return repository.getSearchResults(input);

            }

        });

    }


    public void setQuery(String query) {
        this.query.setValue(query);
    }


    public LiveData<Resource<SearchResponse>> getSearchLiveData() {
        return searchLiveData;
    }
}
