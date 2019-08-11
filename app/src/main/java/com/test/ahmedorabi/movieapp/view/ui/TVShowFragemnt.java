package com.test.ahmedorabi.movieapp.view.ui;

import android.annotation.TargetApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.FragmentTvshowFragemntBinding;
import com.test.ahmedorabi.movieapp.di.Injectable;
import com.test.ahmedorabi.movieapp.model.api.Status;
import com.test.ahmedorabi.movieapp.view.adapter.TVAdapter;
import com.test.ahmedorabi.movieapp.view.callback.TVCallBack;
import com.test.ahmedorabi.movieapp.viewmodel.TVFragmentViewModel;

import java.util.Objects;

import javax.inject.Inject;


public class TVShowFragemnt extends Fragment implements Injectable {


    public static final String TV_VALUE = "tv";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private FragmentTvshowFragemntBinding binding;
    private RecyclerView recyclerView_tvTopRated, recyclerView_tvPopular, recyclerView_tvAiringToday,
            recyclerView_tvOnTheAir, recyclerView_TopAction, recyclerView_topCrime, recyclerView_topComedy, recyclerView_War;
    private TVAdapter tvAdapter;
    private TvFragmentListener mFragmentListener;
    private final TVCallBack callBack = new TVCallBack() {
        @Override
        public void onClickTVSHow(com.test.ahmedorabi.movieapp.model.appModels.tvModel.Result item) {
            mFragmentListener.onFragmentFinish(item.getId(), item.getName(), item.getPosterPath(), item.getOverview(), item.getFirstAirDate(),
                    item.getVoteAverage(), item.getBackdropPath(), item.getOriginalLanguage(), TV_VALUE, item.getVoteCount());

        }
    };

    public TVShowFragemnt() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(context instanceof TvFragmentListener)) throw new AssertionError();
        mFragmentListener = (TvFragmentListener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tvshow_fragemnt, container, false);

        InitViews();

        return binding.getRoot();


    }

    private void InitViews() {

        recyclerView_TopAction = binding.recyclerViewTopAction;
        recyclerView_topComedy = binding.recyclerViewTopComedy;
        recyclerView_topCrime = binding.recyclerViewTopCrime;
        recyclerView_tvAiringToday = binding.recyclerViewAiringToday;
        recyclerView_tvOnTheAir = binding.recyclerViewCurrentlyAiringTVShows;
        recyclerView_War = binding.recyclerViewTopWar;
        recyclerView_tvPopular = binding.recyclerPopularTVShows;
        recyclerView_tvTopRated = binding.recyclerViewTopRatedTVSHOWS;

        recyclerView_TopAction.setItemAnimator(new DefaultItemAnimator());
        recyclerView_TopAction.addItemDecoration(new GridItemDecoration(12));
        recyclerView_TopAction.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView_topComedy.setItemAnimator(new DefaultItemAnimator());
        recyclerView_topComedy.addItemDecoration(new GridItemDecoration(12));
        recyclerView_topComedy.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView_topCrime.setItemAnimator(new DefaultItemAnimator());
        recyclerView_topCrime.addItemDecoration(new GridItemDecoration(12));
        recyclerView_topCrime.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView_tvAiringToday.setItemAnimator(new DefaultItemAnimator());
        recyclerView_tvAiringToday.addItemDecoration(new GridItemDecoration(12));
        recyclerView_tvAiringToday.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView_tvOnTheAir.setItemAnimator(new DefaultItemAnimator());
        recyclerView_tvOnTheAir.addItemDecoration(new GridItemDecoration(12));
        recyclerView_tvOnTheAir.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        recyclerView_War.setItemAnimator(new DefaultItemAnimator());
        recyclerView_War.addItemDecoration(new GridItemDecoration(12));
        recyclerView_War.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        recyclerView_tvPopular.setItemAnimator(new DefaultItemAnimator());
        recyclerView_tvPopular.addItemDecoration(new GridItemDecoration(12));
        recyclerView_tvPopular.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView_tvTopRated.setItemAnimator(new DefaultItemAnimator());
        recyclerView_tvTopRated.addItemDecoration(new GridItemDecoration(12));
        recyclerView_tvTopRated.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TVFragmentViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(TVFragmentViewModel.class);

        checkInternet(viewModel);
    }

    private void checkInternet(TVFragmentViewModel viewModel) {

        if (checkInternetConnection()) {

            binding.setIsConnected(true);

            observeViewModel(viewModel);

        } else {

            binding.setIsConnected(false);


        }
    }

    private void observeViewModel(TVFragmentViewModel viewModel) {

        viewModel.getTopPopularTvObservable().observe(this, tvModelResource -> {

            assert tvModelResource != null;
            if (tvModelResource.status == Status.ERROR || tvModelResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                tvAdapter = new TVAdapter(getActivity(), tvModelResource.data.getResults(), callBack);
                recyclerView_tvPopular.setAdapter(tvAdapter);
            }
        });

        viewModel.getTopActionTvObservable().observe(this, tvModelResource -> {
            assert tvModelResource != null;
            if (tvModelResource.status == Status.ERROR || tvModelResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                tvAdapter = new TVAdapter(getActivity(), tvModelResource.data.getResults(), callBack);
                recyclerView_TopAction.setAdapter(tvAdapter);
            }
        });

        viewModel.getTopRatedTvObservable().observe(this, tvModelResource -> {
            assert tvModelResource != null;
            if (tvModelResource.status == Status.ERROR || tvModelResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                tvAdapter = new TVAdapter(getActivity(), tvModelResource.data.getResults(), callBack);
                recyclerView_tvTopRated.setAdapter(tvAdapter);
            }
        });

        viewModel.getAiringTodayTvObservable().observe(this, tvModelResource -> {
            assert tvModelResource != null;
            if (tvModelResource.status == Status.ERROR || tvModelResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                tvAdapter = new TVAdapter(getActivity(), tvModelResource.data.getResults(), callBack);
                recyclerView_tvAiringToday.setAdapter(tvAdapter);
            }
        });

        viewModel.getOnTheAirTvObservable().observe(this, tvModelResource -> {
            assert tvModelResource != null;
            if (tvModelResource.status == Status.ERROR || tvModelResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                tvAdapter = new TVAdapter(getActivity(), tvModelResource.data.getResults(), callBack);
                recyclerView_tvOnTheAir.setAdapter(tvAdapter);
            }
        });


        viewModel.getTopComedyTvObservable().observe(this, tvModelResource -> {
            assert tvModelResource != null;
            if (tvModelResource.status == Status.ERROR || tvModelResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                tvAdapter = new TVAdapter(getActivity(), tvModelResource.data.getResults(), callBack);
                recyclerView_topComedy.setAdapter(tvAdapter);
            }
        });


        viewModel.getTopCrimeTvObservable().observe(this, tvModelResource -> {
            assert tvModelResource != null;
            if (tvModelResource.status == Status.ERROR || tvModelResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                tvAdapter = new TVAdapter(getActivity(), tvModelResource.data.getResults(), callBack);
                recyclerView_topCrime.setAdapter(tvAdapter);
            }
        });

        viewModel.getTopWarTvObservable().observe(this, tvModelResource -> {
            assert tvModelResource != null;
            if (tvModelResource.status == Status.ERROR || tvModelResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                tvAdapter = new TVAdapter(getActivity(), tvModelResource.data.getResults(), callBack);
                recyclerView_War.setAdapter(tvAdapter);
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private boolean checkInternetConnection() {
        ConnectivityManager cm = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            cm = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        assert cm != null;
        return cm.getActiveNetworkInfo() != null;
    }


    public interface TvFragmentListener {
        void onFragmentFinish(int id, String title, String image, String overview, String release_date, Double vote_average,
                              String backdrop_path, String language, String type, long voteCount);
    }

}
