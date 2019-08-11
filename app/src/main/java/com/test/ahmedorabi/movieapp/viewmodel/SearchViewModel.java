package com.test.ahmedorabi.movieapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;

import com.test.ahmedorabi.movieapp.model.api.Resource;
import com.test.ahmedorabi.movieapp.model.repository.SearchService;
import com.test.ahmedorabi.movieapp.model.appModels.searchModel.SearchResponse;

import javax.inject.Inject;

public class SearchViewModel extends AndroidViewModel {

    private static final MutableLiveData ABSENT = new MutableLiveData();

    static {
        //noinspection unchecked
        ABSENT.setValue(null);
    }


    private final LiveData<Resource<SearchResponse>> searchLiveData;

    private final MutableLiveData<String> query;


    @Inject
    public SearchViewModel(SearchService searchService, @NonNull Application application) {
        super(application);

        this.query = new MutableLiveData<>();

        searchLiveData = Transformations.switchMap(query, input -> {
            if (input.isEmpty()) {
                return ABSENT;
            }
            return searchService.getSearchResults(input);
        });

    }


    public void setQuery(String query) {
        this.query.setValue(query);
    }


    public LiveData<Resource<SearchResponse>> getSearchLiveData() {
        return searchLiveData;
    }
}
