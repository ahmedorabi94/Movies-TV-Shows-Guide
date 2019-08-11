package com.test.ahmedorabi.movieapp.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.FragmentFavouriteBinding;
import com.test.ahmedorabi.movieapp.di.Injectable;
import com.test.ahmedorabi.movieapp.repository.db.Movie;
import com.test.ahmedorabi.movieapp.view.adapter.FavAdapter;
import com.test.ahmedorabi.movieapp.view.callback.FavItemCallBack;
import com.test.ahmedorabi.movieapp.viewmodel.FavouriteViewModel;

import javax.inject.Inject;


public class FavouriteFragment extends Fragment implements Injectable {


    @Inject
    ViewModelProvider.Factory viewModelFactory;
    FavouriteViewModel viewModel;

    private FragmentListener mListener;
    public FavItemCallBack callBack = new FavItemCallBack() {
        @Override
        public void onFavItemClick(Movie item) {
            mListener.onFavFragmentFinish(Integer.parseInt(item.getMovieId()), item.getTitle(), item.getImageUrl(), item.getOverView(), item.getReleaseDate(),
                    item.getVoteAverage(), item.getBackDropPath(), item.getType(), item.getLanguage(), item.getVoteCount());

        }
    };
    private FragmentFavouriteBinding binding;
    private RecyclerView recyclerView;


    public FavouriteFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof FavouriteFragment.FragmentListener)) throw new AssertionError();
        mListener = (FavouriteFragment.FragmentListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavouriteViewModel.class);

        initViews();
        checkFavMovie();


        return binding.getRoot();


    }

    private void initViews() {
        recyclerView = binding.recyclerViewFavourite;
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridItemDecoration(6, 10));
    }

    private void checkFavMovie() {

        viewModel.getAllMovies().observe(this, movies -> {

            if (movies != null) {
                binding.tvFavList.setVisibility(View.GONE);
                FavAdapter favAdapter = new FavAdapter(getActivity(), movies, callBack);
                recyclerView.setAdapter(favAdapter);
            }


        });

    }


    public interface FragmentListener {
        void onFavFragmentFinish(int id, String title, String image, String overview, String release_date, String vote_average,
                                 String backdrop_path, String type, String language, String voteCount);
    }

}
