package com.test.ahmedorabi.movieapp.view.ui;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.ActivitySeasonsBinding;

import java.util.ArrayList;

public class SeasonsActivity extends AppCompatActivity {

    int mSeasons;
    private ArrayList<String> mSeasonsList = new ArrayList<>();
    int idMovie;
    private String imdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySeasonsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_seasons);

        String seasons = getIntent().getStringExtra("seasons");
        idMovie = getIntent().getIntExtra("idTV", 0);
        imdb = getIntent().getStringExtra("imdbIID");

        mSeasons = Integer.parseInt(seasons);

        for (int i = 1; i < mSeasons + 1; i++) {
            mSeasonsList.add("Season " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mSeasonsList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);
                return view;
            }
        };

        binding.listViewSeasons.setAdapter(adapter);


        binding.listViewSeasons.setOnItemClickListener((parent, view, position, id) -> {
            int season = position + 1;
            Intent intent = new Intent(SeasonsActivity.this, SeasonDeatail.class);
            intent.putExtra("seasonNum", season);
            intent.putExtra("idTVShow", idMovie);
            intent.putExtra("imdbb", imdb);
            startActivity(intent);

        });

    }
}
