package com.test.ahmedorabi.movieapp.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.SeasonRowItemBinding;
import com.test.ahmedorabi.movieapp.repository.data.seasonResponse.Episode;
import com.test.ahmedorabi.movieapp.view.callback.EpisodeCallback;

import java.util.List;

public class EpisodeAdapter extends ArrayAdapter<Episode> {

    private List<Episode> data;
    private EpisodeCallback mCallback;

    public EpisodeAdapter(Context context, List<Episode> items, EpisodeCallback callback) {
        super(context, 0, items);
        data = items;
        this.mCallback = callback;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder") SeasonRowItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.season_row_item, parent, false);


        binding.setEpisodeItem(data.get(position));
        binding.executePendingBindings();
        binding.setCallback(mCallback);



        return binding.getRoot();


    }
}
