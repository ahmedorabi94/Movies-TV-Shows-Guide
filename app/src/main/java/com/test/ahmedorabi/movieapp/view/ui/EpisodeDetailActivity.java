package com.test.ahmedorabi.movieapp.view.ui;

import android.annotation.SuppressLint;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.ActivityEpisodeDetailBinding;
import com.test.ahmedorabi.movieapp.repository.data.TVType;
import com.test.ahmedorabi.movieapp.api.Status;
import com.test.ahmedorabi.movieapp.repository.data.imagesResponse.Still;
import com.test.ahmedorabi.movieapp.view.adapter.ImageAdapter;
import com.test.ahmedorabi.movieapp.view.callback.EpisodeImageCallback;
import com.test.ahmedorabi.movieapp.viewmodel.EpisodeDetailViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class EpisodeDetailActivity extends AppCompatActivity {

    public EpisodeImageCallback callback = still -> {
        Intent intent = new Intent(EpisodeDetailActivity.this, DisplayImage.class);
        intent.putExtra("imageUrl", still.getFilePath());
        intent.putExtra("imageType", "backdrop");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    };
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private List<Still> stillList;
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private ActivityEpisodeDetailBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_episode_detail);


        int idTv = getIntent().getIntExtra("TVIDD", 0);
        int mSeasonNum = getIntent().getIntExtra("SNum", 0);
        long episodeNum = getIntent().getLongExtra("EpNum", 0);
        String name = getIntent().getStringExtra("mName");
        String path = getIntent().getStringExtra("stillPath");
        String overView = getIntent().getStringExtra("mOverview");

        initViews();

        AndroidInjection.inject(this);


        binding.tvOverViewEpisode.setText(overView);
        binding.tvNameEpisode.setText(episodeNum + " " + name);

        String baseUrl = "http://image.tmdb.org/t/p/w500";
        String finalUrl = baseUrl + path;
        Glide.with(this).load(finalUrl).into(binding.ImageViewEpisode);


        final EpisodeDetailViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(EpisodeDetailViewModel.class);

        viewModel.setType(new TVType(idTv, mSeasonNum, episodeNum));

        observeImages(viewModel);


    }

    private void initViews() {
        recyclerView = binding.RecyclerViewImages;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridItemDecoration(12));
        recyclerView.setLayoutManager(new LinearLayoutManager(EpisodeDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void observeImages(EpisodeDetailViewModel viewModel) {

        viewModel.getImagesResponseLiveData().observe(this, imagesResponseResource -> {

            assert imagesResponseResource != null;
            if (imagesResponseResource.status == Status.ERROR || imagesResponseResource.data == null) {
                Toast.makeText(getApplicationContext(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                stillList = imagesResponseResource.data.getStills();
                imageAdapter = new ImageAdapter(EpisodeDetailActivity.this, stillList, callback);
                recyclerView.setAdapter(imageAdapter);
            }

        });

    }


}
