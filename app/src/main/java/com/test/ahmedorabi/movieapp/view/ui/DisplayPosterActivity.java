package com.test.ahmedorabi.movieapp.view.ui;

import android.annotation.SuppressLint;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.ActivityDisplayPosterBinding;
import com.test.ahmedorabi.movieapp.model.MovieType;
import com.test.ahmedorabi.movieapp.model.api.Status;
import com.test.ahmedorabi.movieapp.model.appModels.ActorImages.Profile;
import com.test.ahmedorabi.movieapp.model.appModels.backdropsModel.Poster;
import com.test.ahmedorabi.movieapp.viewmodel.PosterViewModel;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.AndroidInjection;


public class DisplayPosterActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private List<Poster> posterList;
    private ViewPagerAdapter pagerAdapter;
    private List<Profile> profileList;
    private int actorId;
    private ActivityDisplayPosterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_poster);

        AndroidInjection.inject(this);


        actorId = getIntent().getIntExtra("imagesId", 0);

        Intent intent = getIntent();
        int id = getIntent().getIntExtra("id", 0);
        String type = intent.getStringExtra("type");


        final PosterViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(PosterViewModel.class);
        viewModel.setMovieType(new MovieType(id, type, actorId));

        observeViewModel(viewModel);






    }



    private void observeViewModel(PosterViewModel viewModel) {


        if (actorId != 0) {
            viewModel.getActorImages().observe(this, response -> {
                assert response != null;
                if (response.status == Status.ERROR || response.data == null) {
                    Toast.makeText(getApplicationContext(), "network failure.", Toast.LENGTH_SHORT).show();
                } else {
                    profileList = response.data.getProfiles();
                    if (profileList.size() > 0) {
                        pagerAdapter = new ViewPagerAdapter(getApplicationContext());
                        binding.viewPagerPoster.setAdapter(pagerAdapter);
                        binding.tabPosters.setupWithViewPager(binding.viewPagerPoster, true);
                    }
                }

            });
        } else {
            viewModel.getBackdrops().observe(this, response -> {
                assert response != null;
                if (response.status == Status.ERROR || response.data == null) {
                    Toast.makeText(getApplicationContext(), "network failure.", Toast.LENGTH_SHORT).show();
                } else {
                    posterList = response.data.getPosters();
                    if (posterList.size() > 0) {
                        pagerAdapter = new ViewPagerAdapter(getApplicationContext());
                        binding.viewPagerPoster.setAdapter(pagerAdapter);
                        binding.tabPosters.setupWithViewPager(binding.viewPagerPoster, true);
                    }
                }
            });
        }


    }

    class ViewPagerAdapter extends PagerAdapter {
        private Context context;
        private LayoutInflater layoutInflater;


        ViewPagerAdapter(Context context) {
            this.context = context;
        }


        @Override
        public int getCount() {
            if (actorId != 0) {
                return profileList.size();
            } else {
                return posterList.size();

            }
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.poster_item, null);
            ImageView imageView = view.findViewById(R.id.viewPager_posterItem);

            final String file_path;
            String url;

            if (actorId != 0) {
                file_path = profileList.get(position).getFilePath();
                url = "http://image.tmdb.org/t/p/w780";

            } else {
                file_path = posterList.get(position).getFilePath();
                url = "http://image.tmdb.org/t/p/w780";

            }


            final String finalUrl = url + file_path;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Glide.with(Objects.requireNonNull(getApplicationContext()).getApplicationContext()).load(finalUrl).into(imageView);
            }

            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);


            imageView.setOnClickListener(v -> {
                Intent intent = new Intent(DisplayPosterActivity.this, DisplayImage.class);
                intent.putExtra("imageUrl", file_path);
                intent.putExtra("imageType", "poster");
                startActivity(intent);

            });

            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ViewPager viewPager = (ViewPager) container;
            View view = (View) object;
            viewPager.removeView(view);

        }
    }


}
