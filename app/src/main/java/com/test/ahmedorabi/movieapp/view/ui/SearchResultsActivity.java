package com.test.ahmedorabi.movieapp.view.ui;

import android.app.SearchManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.widget.SearchView;
import android.widget.Toast;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.ActivitySearchResultsBinding;
import com.test.ahmedorabi.movieapp.api.Status;
import com.test.ahmedorabi.movieapp.repository.data.searchModel.Result;
import com.test.ahmedorabi.movieapp.view.adapter.FilterMovieAdapter;
import com.test.ahmedorabi.movieapp.view.callback.SearchCallback;
import com.test.ahmedorabi.movieapp.viewmodel.SearchViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SearchResultsActivity extends AppCompatActivity {

    public SearchCallback callback = item -> {
        Intent intent = new Intent(SearchResultsActivity.this, DetailActivity.class);

        if (item.getMediaType().equals("movie")) {
            intent.putExtra("title", item.getTitle());
        } else {
            intent.putExtra("title", item.getName());
        }

        intent.putExtra("image", item.getPosterPath());
        intent.putExtra("overview", item.getOverview());
        intent.putExtra("release_date", item.getReleaseDate());
        intent.putExtra("vote_average", item.getVoteAverage());
        intent.putExtra("id", item.getId());
        intent.putExtra("backdrop_path", item.getBackdropPath());
        intent.putExtra("type", item.getMediaType());
        intent.putExtra("language", item.getOriginalLanguage());
        intent.putExtra("count", item.getVoteCount());
        startActivity(intent);
    };
    ActivitySearchResultsBinding binding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    SearchViewModel viewModel;
    private FilterMovieAdapter filterMovieAdapter;
    private RecyclerView recyclerView_filter;
    private List<Result> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_results);

        AndroidInjection.inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);

        initViews();

    }


    private void initViews() {
        recyclerView_filter = binding.recyclerViewFilter;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_filter.setLayoutManager(mLayoutManager);
        recyclerView_filter.setItemAnimator(new DefaultItemAnimator());
        recyclerView_filter.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.start_search).getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.onActionViewExpanded();

        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setSubmitButtonEnabled(true);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                loadData(newText);
                return true;
            }
        });


        return true;
    }

    private void loadData(String query) {

        if (query.length() > 0) {

            viewModel.setQuery(query);

            viewModel.getSearchLiveData().observe(this, response -> {
                assert response != null;
                if (response.status == Status.ERROR || response.data == null) {
                    Toast.makeText(getApplicationContext(), "network failure.", Toast.LENGTH_SHORT).show();
                } else {
                    filteredList = response.data.getResults();
                    filterMovieAdapter = new FilterMovieAdapter(getApplicationContext(), filteredList, callback);
                    recyclerView_filter.setAdapter(filterMovieAdapter);
                }

            });

        }
    }


}