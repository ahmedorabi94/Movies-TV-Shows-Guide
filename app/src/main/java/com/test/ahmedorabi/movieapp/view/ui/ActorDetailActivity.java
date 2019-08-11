package com.test.ahmedorabi.movieapp.view.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.ActivityActorDetailBinding;
import com.test.ahmedorabi.movieapp.api.Status;
import com.test.ahmedorabi.movieapp.repository.data.knownResponse.Cast;
import com.test.ahmedorabi.movieapp.view.adapter.GridViewAdapter;
import com.test.ahmedorabi.movieapp.view.callback.KnownCallback;
import com.test.ahmedorabi.movieapp.viewmodel.PersonViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ActorDetailActivity extends AppCompatActivity {

    public KnownCallback callback = item -> {

        String title = item.getTitle();

        Intent intent = new Intent(ActorDetailActivity.this, DetailActivity.class);

        if (!TextUtils.isEmpty(title)) {
            intent.putExtra("title", item.getTitle());
            intent.putExtra("type", "movie");

        } else {
            intent.putExtra("title", item.getmName());
            intent.putExtra("type", "tv");

        }

        intent.putExtra("image", item.getPosterPath());
        intent.putExtra("overview", item.getOverview());
        intent.putExtra("release_date", item.getReleaseDate());
        intent.putExtra("vote_average", item.getVoteAverage());
        intent.putExtra("id", item.getId());
        intent.putExtra("backdrop_path", item.getBackdropPath());
        intent.putExtra("language", item.getOriginalLanguage());
        intent.putExtra("count", item.getVoteCount());

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    };
    private ActivityActorDetailBinding binding;
    private int actorId;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private RecyclerView recyclerView, recyclerViewTv;
    private GridViewAdapter adapter;
    private List<Cast> castListmovies, castListTv;

    private String finalUrl;
    private String baseUrl = "http://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_actor_detail);

        initViews();

        MyHandlers handlers = new MyHandlers(this);

        actorId = getIntent().getIntExtra("id", 0);
        String actorName = getIntent().getStringExtra("actorName");

        setTitle(actorName);

        AndroidInjection.inject(this);


        final PersonViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(PersonViewModel.class);
        viewModel.setID(String.valueOf(actorId));


        binding.setPersonViewModel(viewModel);
        binding.setHandlers(handlers);

        observeViewModel(viewModel);


    }

    private void observeViewModel(final PersonViewModel viewModel) {


        viewModel.getKnownResponseMoviesLiveData().observe(this, knownResponseResource -> {

            assert knownResponseResource != null;
            if (knownResponseResource.status == Status.ERROR || knownResponseResource.data == null) {
                Toast.makeText(getApplicationContext(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                castListmovies = knownResponseResource.data.getCast();
                adapter = new GridViewAdapter(getApplicationContext(), castListmovies, callback);
                recyclerView.setAdapter(adapter);
            }

        });

        viewModel.getKnownResponseTVLiveData().observe(this, knownResponseResource -> {

            assert knownResponseResource != null;
            if (knownResponseResource.status == Status.ERROR || knownResponseResource.data == null) {
                Toast.makeText(getApplicationContext(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                castListTv = knownResponseResource.data.getCast();
                adapter = new GridViewAdapter(getApplicationContext(), castListTv, callback);
                recyclerViewTv.setAdapter(adapter);
            }
        });


        viewModel.getPersonResponseLiveData().observe(this, personResponseResource -> {
            assert personResponseResource != null;
            if (personResponseResource.status == Status.ERROR || personResponseResource.data == null) {
                Toast.makeText(getApplicationContext(), "network failure.", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.setPerson(personResponseResource.data);
                finalUrl = baseUrl + personResponseResource.data.getProfilePath();
                Glide.with(getApplicationContext()).load(finalUrl).into(binding.actorImageDetail);
            }
        });

    }


    private void initViews() {

        recyclerView = binding.recyclerViewActor;
        recyclerViewTv = binding.recyclerViewActorTv;

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTv.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridItemDecoration(12));
        recyclerViewTv.addItemDecoration(new GridItemDecoration(12));
        recyclerView.setLayoutManager(new LinearLayoutManager(ActorDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTv.setLayoutManager(new LinearLayoutManager(ActorDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));

    }

    public class MyHandlers {
        Context context;

        MyHandlers(Context context) {
            this.context = context;
        }

        public void onImageClick(View view) {
            Intent intent = new Intent(ActorDetailActivity.this, DisplayPosterActivity.class);
            intent.putExtra("imagesId", actorId);
            startActivity(intent);
        }
    }

}
