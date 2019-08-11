package com.test.ahmedorabi.movieapp.view.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.ActivitySeasonDeatailBinding;
import com.test.ahmedorabi.movieapp.model.TVType;
import com.test.ahmedorabi.movieapp.model.api.Status;
import com.test.ahmedorabi.movieapp.model.appModels.seasonResponse.Episode;
import com.test.ahmedorabi.movieapp.view.adapter.EpisodeAdapter;
import com.test.ahmedorabi.movieapp.view.callback.EpisodeCallback;
import com.test.ahmedorabi.movieapp.viewmodel.EpisodeDetailViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SeasonDeatail extends AppCompatActivity {

    int number;
    int idMovie;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private String imdb;
    private List<Episode> episodeList;
    private EpisodeAdapter episodeAdapter;
    private ActivitySeasonDeatailBinding binding;
    private EpisodeCallback callback = new EpisodeCallback() {
        @Override
        public void onEpisodeClick(Episode episode) {
            Intent intent = new Intent(SeasonDeatail.this, EpisodeDetailActivity.class);
            intent.putExtra("TVIDD", idMovie);
            intent.putExtra("SNum", number);
            intent.putExtra("iimdb", imdb);
            intent.putExtra("EpNum", episode.getEpisodeNumber());
            intent.putExtra("mName", episode.getName());
            intent.putExtra("mOverview", episode.getOverview());
            intent.putExtra("stillPath", episode.getStillPath());
            intent.putExtra("mVoteaverage", episode.getVoteAverage());
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_season_deatail);


        AndroidInjection.inject(this);

        number = getIntent().getIntExtra("seasonNum", 0);
        idMovie = getIntent().getIntExtra("idTVShow", 0);
        imdb = getIntent().getStringExtra("imdbb");

        setTitle("Season " + number);


        final EpisodeDetailViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(EpisodeDetailViewModel.class);

        viewModel.setType(new TVType(idMovie, number));

        observeSeason(viewModel);


    }

    private void observeSeason(EpisodeDetailViewModel viewModel) {

        viewModel.getSeasonResponseLiveData().observe(this, seasonResponseResource -> {
            assert seasonResponseResource != null;
            if (seasonResponseResource.status == Status.ERROR || seasonResponseResource.data == null) {
                Toast.makeText(getApplicationContext(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                episodeList = seasonResponseResource.data.getEpisodes();
                episodeAdapter = new EpisodeAdapter(SeasonDeatail.this, episodeList, callback);
                binding.listviewSeasonDeatail.setAdapter(episodeAdapter);
            }
        });

    }
}
