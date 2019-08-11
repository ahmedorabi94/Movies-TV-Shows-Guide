package com.test.ahmedorabi.movieapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.test.ahmedorabi.movieapp.model.api.Resource;
import com.test.ahmedorabi.movieapp.model.repository.TVFragmentService;
import com.test.ahmedorabi.movieapp.model.appModels.tvModel.TvModel;

import javax.inject.Inject;

public class TVFragmentViewModel extends AndroidViewModel {

    private final LiveData<Resource<TvModel>> TopRatedTvObservable;
    private final LiveData<Resource<TvModel>> TopPopularTvObservable;
    private final LiveData<Resource<TvModel>> OnTheAirTvObservable;
    private final LiveData<Resource<TvModel>> TopActionTvObservable;
    private final LiveData<Resource<TvModel>> AiringTodayTvObservable;
    private final LiveData<Resource<TvModel>> TopCrimeTvObservable;
    private final LiveData<Resource<TvModel>> TopComedyTvObservable;
    private final LiveData<Resource<TvModel>> TopWarTvObservable;



    @Inject
    public TVFragmentViewModel(TVFragmentService service , @NonNull Application application) {
        super(application);
        TopActionTvObservable = service.getTopActionTv();
        TopComedyTvObservable = service.getTopComedyTv();
        TopRatedTvObservable = service.getTopRatedTv();
        TopPopularTvObservable = service.getPopularTv();
        OnTheAirTvObservable = service.getOnTheAirTv();
        AiringTodayTvObservable = service.getAiringTodayTv();
        TopCrimeTvObservable = service.getTopCrimeTv();
        TopWarTvObservable = service.getTopWarTv();
    }

    public LiveData<Resource<TvModel>> getTopPopularTvObservable() {
        return TopPopularTvObservable;
    }

    public LiveData<Resource<TvModel>>getAiringTodayTvObservable() {
        return AiringTodayTvObservable;
    }

    public LiveData<Resource<TvModel>> getOnTheAirTvObservable() {
        return OnTheAirTvObservable;
    }

    public LiveData<Resource<TvModel>> getTopActionTvObservable() {
        return TopActionTvObservable;
    }

    public LiveData<Resource<TvModel>> getTopComedyTvObservable() {
        return TopComedyTvObservable;
    }

    public LiveData<Resource<TvModel>> getTopCrimeTvObservable() {
        return TopCrimeTvObservable;
    }

    public LiveData<Resource<TvModel>> getTopRatedTvObservable() {
        return TopRatedTvObservable;
    }

    public LiveData<Resource<TvModel>> getTopWarTvObservable() {
        return TopWarTvObservable;
    }
}
