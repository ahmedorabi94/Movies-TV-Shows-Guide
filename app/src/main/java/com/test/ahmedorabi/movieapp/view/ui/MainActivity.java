package com.test.ahmedorabi.movieapp.view.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.ahmedorabi.movieapp.BuildConfig;
import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.api.Status;
import com.test.ahmedorabi.movieapp.repository.data.moviemodel.Result;
import com.test.ahmedorabi.movieapp.viewmodel.MoviesFragmentViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements MoviesFragment.FragmentListener, DetailActivityFragment.DetailFragmentListener, TVShowFragemnt.TvFragmentListener, HasSupportFragmentInjector {

    public static final String API_KEY = "57d4b3dbc3a3685568008e60";

    public static final String TYPE = "type";
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private boolean mtablet;
    private List<Result> nowPlayingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup fragemntContainer = findViewById(R.id.detail_fragment_container);

        mtablet = (fragemntContainer != null);


        if (mtablet) {
            final MoviesFragmentViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesFragmentViewModel.class);
            observeViewModel(viewModel);
        }


    }

    private void observeViewModel(MoviesFragmentViewModel viewModel) {

        viewModel.getNowPlayingObservable().observe(this, response -> {

            assert response != null;
            if (response.status == Status.ERROR || response.data == null) {
                Toast.makeText(getApplicationContext(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                nowPlayingList = response.data.getResults();

                Result item = nowPlayingList.get(0);

                Bundle arguments = new Bundle();
                arguments.putInt("id", item.getId());
                arguments.putString("title", item.getTitle());
                arguments.putString("image", item.getPosterPath());
                arguments.putString("overview", item.getOverview());
                arguments.putString("release_date", item.getReleaseDate());
                arguments.putDouble("vote_average", item.getVoteAverage());
                arguments.putString("backdrop_path", item.getBackdropPath());
                arguments.putString(TYPE, "movie");
                arguments.putString("language", item.getOriginalLanguage());
                arguments.putLong("count", item.getVoteCount());

                DetailActivityFragment detailActivityFragment = new DetailActivityFragment();
                detailActivityFragment.setArguments(arguments);

                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.detail_fragment_container);

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.detail_fragment_container, detailActivityFragment)
                        .commit();
            }


        });

    }


    @Override
    public void onFragmentFinish(int id, String title, String image, String overview, String release_date, Double vote_average, String backdrop_path, String language, String type, long voteCount) {

        if (mtablet) {

            Bundle arguments = new Bundle();
            arguments.putInt("id", id);
            arguments.putString("title", title);
            arguments.putString("image", image);
            arguments.putString("overview", overview);
            arguments.putString("release_date", release_date);
            arguments.putDouble("vote_average", vote_average);
            arguments.putString("backdrop_path", backdrop_path);
            arguments.putString(TYPE, type);
            arguments.putString("language", language);
            arguments.putLong("count", voteCount);

            DetailActivityFragment detailActivityFragment = new DetailActivityFragment();
            detailActivityFragment.setArguments(arguments);

            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.detail_fragment_container);

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_fragment_container, detailActivityFragment)
                    .commit();

        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("image", image);
            intent.putExtra("overview", overview);
            intent.putExtra("release_date", release_date);
            intent.putExtra("vote_average", vote_average);
            intent.putExtra("id", id);
            intent.putExtra("backdrop_path", backdrop_path);
            intent.putExtra(TYPE, type);
            intent.putExtra("language", language);
            intent.putExtra("count", voteCount);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.search) {
            Intent intent = new Intent(this, SearchResultsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDetailFragmentFinish(int id_movie, String type) {
        Intent intent = new Intent(this, ReveiwActivity.class);
        intent.putExtra("id_movie_review", id_movie);
        intent.putExtra("type", type);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    @Override
    public void onDetailActorFinish(int id, String name) {
        Intent intent = new Intent(this, ActorDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("actorName", name);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    @Override
    public void onSimilarFragmentFinish(int id, String title, String image, String overview, String release_date, Double vote_average, String backdrop_path, String language, String type, long voteCount) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("image", image);
        intent.putExtra("overview", overview);
        intent.putExtra("release_date", release_date);
        intent.putExtra("vote_average", vote_average);
        intent.putExtra("id", id);
        intent.putExtra("backdrop_path", backdrop_path);
        intent.putExtra(TYPE, type);
        intent.putExtra("language", language);
        intent.putExtra("count", voteCount);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);


    }

    @Override
    public void DisplayImage(String url, String type) {
        Intent intent = new Intent(this, DisplayImage.class);
        intent.putExtra("imageUrl", url);
        intent.putExtra("imageType", type);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    @Override
    public void DisplayPoster(int id, String type) {
        Intent intent = new Intent(this, DisplayPosterActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

    }


    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
