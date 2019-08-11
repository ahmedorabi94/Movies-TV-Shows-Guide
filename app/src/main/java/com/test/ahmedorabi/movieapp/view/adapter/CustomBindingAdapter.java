package com.test.ahmedorabi.movieapp.view.adapter;

import androidx.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;

public class CustomBindingAdapter {

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    @BindingAdapter("posterImage")
    public static void LoadPoster(ImageView view, String url) {

        String baseUrl = "http://image.tmdb.org/t/p/w500";
        String finalUrl = baseUrl + url;
        Glide.with(view.getContext()).load(finalUrl).into(view);
    }


    @BindingAdapter("voteAverage")
    public static void setVote(RatingBar view, Double vote) {
        if (vote != null) {
            float rate = (float) (vote / 2.0);
            view.setRating(rate);
        } else {
            view.setRating(0);
        }

    }

    @BindingAdapter("voteAverageDb")
    public static void setVote(RatingBar view, String oldVote) {

        double vote = Double.parseDouble(oldVote);


        if (vote != 0) {
            float rate = (float) (vote / 2.0);
            view.setRating(rate);
        } else {
            view.setRating(0);
        }

    }



}
