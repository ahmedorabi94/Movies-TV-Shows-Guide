package com.test.ahmedorabi.movieapp.view.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;

import com.test.ahmedorabi.movieapp.R;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Ahmed Orabi on 12/09/2016.
 */
public class DetailActivity extends AppCompatActivity implements DetailActivityFragment.DetailFragmentListener , HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
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
    public void onDetailActorFinish(int id,String name) {
        Intent intent = new Intent(this, ActorDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("actorName",name);
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
        intent.putExtra("type", type);
        intent.putExtra("language", language);
        intent.putExtra("count", voteCount);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

    }

    @Override
    public void DisplayImage(String url,String type) {
        Intent intent = new Intent(this, DisplayImage.class);
        intent.putExtra("imageUrl", url);
        intent.putExtra("imageType",type);
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


