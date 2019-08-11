package com.test.ahmedorabi.movieapp.view.ui;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.test.ahmedorabi.movieapp.R;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class FavouriteActivity extends AppCompatActivity implements FavouriteFragment.FragmentListener, FavouriteDetailFragment.FavDetailFragmentListener , HasSupportFragmentInjector {

    public static final String TYPE = "type";
    private boolean mtablet;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        ViewGroup fragemntContainer = findViewById(R.id.favourite_detail_container);
        mtablet = (fragemntContainer != null);
    }

    @Override
    public void onFavFragmentFinish(int id, String title, String image, String overview, String release_date, String vote_average, String backdrop_path, String type, String language, String voteCount) {


        if (mtablet) {
            Bundle arguments = new Bundle();
            arguments.putInt("id", id);
            arguments.putString("title", title);
            arguments.putString("image", image);
            arguments.putString("overview", overview);
            arguments.putString("release_date", release_date);
            arguments.putDouble("vote_average", Double.parseDouble(vote_average));
            arguments.putString("backdrop_path", backdrop_path);
            arguments.putString(TYPE, type);
            arguments.putString("language", language);
            arguments.putLong("count", Long.parseLong(voteCount));

            FavouriteDetailFragment detailFragment = new FavouriteDetailFragment();
            detailFragment.setArguments(arguments);

            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.favourite_detail_container);

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.favourite_detail_container, detailFragment)
                    .commit();
        } else {

            Intent intent = new Intent(this, FavDetailActivity.class);
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
    public void onFavDetailFragmentFinish(int id_movie, String type) {
        Intent intent = new Intent(this, ReveiwActivity.class);
        intent.putExtra("id_movie_review", id_movie);
        intent.putExtra("type", type);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

    }

    @Override
    public void onFavDetailActorFinish(int id,String name) {
        Intent intent = new Intent(this, ActorDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("actorName",name);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

    }

    @Override
    public void onSimilarFavFragmentFinish(int id, String title, String image, String overview, String release_date, Double vote_average, String backdrop_path, String language, String type, long voteCount) {
        Intent intent = new Intent(this, FavDetailActivity.class);
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
    public void DisplayImage(String url,String type) {
        Intent intent = new Intent(this, DisplayImage.class);
        intent.putExtra("imageUrl", url);
        intent.putExtra("imageType",type);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    @Override
    public void DisplayPosterFav(int id, String type) {
        Intent intent = new Intent(this, DisplayPosterActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }



    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
