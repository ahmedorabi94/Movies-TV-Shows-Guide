package com.test.ahmedorabi.movieapp.view.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.ActivityReveiwBinding;
import com.test.ahmedorabi.movieapp.model.MovieType;
import com.test.ahmedorabi.movieapp.model.api.Status;
import com.test.ahmedorabi.movieapp.model.appModels.ReveiwItem;
import com.test.ahmedorabi.movieapp.model.appModels.reviewModel.Result;
import com.test.ahmedorabi.movieapp.view.adapter.ReviewAdapter;
import com.test.ahmedorabi.movieapp.viewmodel.ReviewViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ReveiwActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ActivityReveiwBinding binding;
    private ArrayList<ReveiwItem> reveiwItems;
    private ReviewAdapter reviewAdapter;
    private List<Result> reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reveiw);
        reveiwItems = new ArrayList<>();

        AndroidInjection.inject(this);

        int id_movie = getIntent().getIntExtra("id_movie_review", 0);
        String type = getIntent().getStringExtra("type");


        final ReviewViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReviewViewModel.class);
        viewModel.setMovieType(new MovieType(id_movie, type));


        observeReviews(viewModel);


    }


    private void observeReviews(ReviewViewModel viewModel) {

        viewModel.getReviewResponseLiveData().observe(this, reviewResponse -> {

            assert reviewResponse != null;
            if (reviewResponse.status == Status.ERROR || reviewResponse.data == null) {
                Toast.makeText(getApplicationContext(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                reviewList = reviewResponse.data.getResults();
                if (reviewList.size() > 0) {
                    for (Result item : reviewList) {
                        String author = item.getAuthor();
                        String content = item.getContent();
                        reveiwItems.add(new ReveiwItem(author, content));
                    }
                }
                reviewAdapter = new ReviewAdapter(getApplicationContext(), reveiwItems);
                binding.listViewReview.setAdapter(reviewAdapter);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
