package com.test.ahmedorabi.movieapp.view.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
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
import com.test.ahmedorabi.movieapp.databinding.FragmentMoviesBinding;
import com.test.ahmedorabi.movieapp.di.Injectable;
import com.test.ahmedorabi.movieapp.api.Status;
import com.test.ahmedorabi.movieapp.repository.data.moviemodel.Result;
import com.test.ahmedorabi.movieapp.view.adapter.MovieAdapter;
import com.test.ahmedorabi.movieapp.view.callback.MovieCallback;
import com.test.ahmedorabi.movieapp.viewmodel.MoviesFragmentViewModel;

import java.util.Objects;

import javax.inject.Inject;


public class MoviesFragment extends Fragment implements Injectable {

    public static final String MOVIE_VALUE = "movie";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private FragmentMoviesBinding binding;
    private FragmentListener mListener;
    private final MovieCallback movieCallback = new MovieCallback() {
        @Override
        public void onClickMovie(Result item) {
            mListener.onFragmentFinish(item.getId(), item.getTitle(), item.getPosterPath(), item.getOverview(), item.getReleaseDate(),
                    item.getVoteAverage(), item.getBackdropPath(), item.getOriginalLanguage(), MOVIE_VALUE, item.getVoteCount());

        }
    };
    private RecyclerView recyclerView, recyclerView_topRated, recyclerView_Upcoming, recyclerView_Popular, recyclerView_topHorroMovies, recyclerView_topActionMovies, recyclerView_topRamance;
    private MovieAdapter movieAdapter;


    MoviesFragmentViewModel viewModel;


    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(context instanceof MoviesFragment.FragmentListener)) throw new AssertionError();
        mListener = (MoviesFragment.FragmentListener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);
        initViews();


        return binding.getRoot();
    }

    private void initViews() {

        recyclerView = binding.recyclerViewMain;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridItemDecoration(12));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView_topRated = binding.recyclerViewTopRated;
        recyclerView_topRated.setItemAnimator(new DefaultItemAnimator());
        recyclerView_topRated.addItemDecoration(new GridItemDecoration(12));
        recyclerView_topRated.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        recyclerView_Popular = binding.recyclerViewPopularMovies;
        recyclerView_Popular.setItemAnimator(new DefaultItemAnimator());
        recyclerView_Popular.addItemDecoration(new GridItemDecoration(12));
        recyclerView_Popular.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        recyclerView_Upcoming = binding.recyclerViewUpComing;
        recyclerView_Upcoming.setItemAnimator(new DefaultItemAnimator());
        recyclerView_Upcoming.addItemDecoration(new GridItemDecoration(12));
        recyclerView_Upcoming.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        recyclerView_topHorroMovies = binding.recyclerViewTopHorrorMovies;
        recyclerView_topHorroMovies.setItemAnimator(new DefaultItemAnimator());
        recyclerView_topHorroMovies.addItemDecoration(new GridItemDecoration(12));
        recyclerView_topHorroMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView_topActionMovies = binding.recyclerViewTopActionMovies;
        recyclerView_topActionMovies.setItemAnimator(new DefaultItemAnimator());
        recyclerView_topActionMovies.addItemDecoration(new GridItemDecoration(12));
        recyclerView_topActionMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView_topRamance = binding.recyclerViewTopRomanceMovies;
        recyclerView_topRamance.setItemAnimator(new DefaultItemAnimator());
        recyclerView_topRamance.addItemDecoration(new GridItemDecoration(12));
        recyclerView_topRamance.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesFragmentViewModel.class);

        checkInternet(viewModel);

    }


    private void checkInternet(MoviesFragmentViewModel viewModel) {

        if (checkInternetConnection()) {

            binding.setIsConnected(true);

            observeViewModel(viewModel);

        } else {
            binding.setIsConnected(false);

        }
    }


    private void observeViewModel(MoviesFragmentViewModel viewModel) {

        viewModel.getNowPlayingObservable().observe(this, movieResponseResource -> {
            assert movieResponseResource != null;
            if (movieResponseResource.status == Status.ERROR) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else if (movieResponseResource.status == Status.SUCCESS){
                assert movieResponseResource.data != null;
                movieAdapter = new MovieAdapter(getActivity(), movieResponseResource.data.getResults(), movieCallback);
                recyclerView.setAdapter(movieAdapter);
            }






        });

        viewModel.getTopRatedObservable().observe(this, movieResponseResource -> {
            assert movieResponseResource != null;
            if (movieResponseResource.status == Status.ERROR) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else if (movieResponseResource.status == Status.SUCCESS){
                assert movieResponseResource.data != null;
                movieAdapter = new MovieAdapter(getActivity(), movieResponseResource.data.getResults(), movieCallback);
                recyclerView_topRated.setAdapter(movieAdapter);
            }

        });

        viewModel.getUpcomingObservable().observe(this, movieResponseResource -> {
            assert movieResponseResource != null;
            if (movieResponseResource.status == Status.ERROR) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else if (movieResponseResource.status == Status.SUCCESS){
                assert movieResponseResource.data != null;
                movieAdapter = new MovieAdapter(getActivity(), movieResponseResource.data.getResults(), movieCallback);
                recyclerView_Upcoming.setAdapter(movieAdapter);
            }
        });


        viewModel.getPopularObservable().observe(this, movieResponseResource -> {
            assert movieResponseResource != null;
            if (movieResponseResource.status == Status.ERROR || movieResponseResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else if (movieResponseResource.status == Status.SUCCESS){
                movieAdapter = new MovieAdapter(getActivity(), movieResponseResource.data.getResults(), movieCallback);
                recyclerView_Popular.setAdapter(movieAdapter);
            }
        });


        viewModel.getTopHorroObservable().observe(this, movieResponseResource -> {
            assert movieResponseResource != null;
            if (movieResponseResource.status == Status.ERROR || movieResponseResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else if (movieResponseResource.status == Status.SUCCESS){
                movieAdapter = new MovieAdapter(getActivity(), movieResponseResource.data.getResults(), movieCallback);
                recyclerView_topHorroMovies.setAdapter(movieAdapter);
            }
        });

        viewModel.getTopActionObservable().observe(this, movieResponseResource -> {
            assert movieResponseResource != null;
            if (movieResponseResource.status == Status.ERROR || movieResponseResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else if (movieResponseResource.status == Status.SUCCESS){
                movieAdapter = new MovieAdapter(getActivity(), movieResponseResource.data.getResults(), movieCallback);
                recyclerView_topActionMovies.setAdapter(movieAdapter);
            }
        });

        viewModel.getTopRomanceObservable().observe(this, movieResponseResource -> {
            assert movieResponseResource != null;
            if (movieResponseResource.status == Status.ERROR || movieResponseResource.data == null) {
                Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();
            } else if (movieResponseResource.status == Status.SUCCESS){
                movieAdapter = new MovieAdapter(getActivity(), movieResponseResource.data.getResults(), movieCallback);
                recyclerView_topRamance.setAdapter(movieAdapter);
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


    public interface FragmentListener {
        void onFragmentFinish(int id, String title, String image, String overview, String release_date, Double vote_average,
                              String backdrop_path, String language, String type, long voteCount);
    }


}
