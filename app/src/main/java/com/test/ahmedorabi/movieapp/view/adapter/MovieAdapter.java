package com.test.ahmedorabi.movieapp.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.GridViewItemBinding;
import com.test.ahmedorabi.movieapp.repository.data.moviemodel.Result;
import com.test.ahmedorabi.movieapp.view.callback.MovieCallback;

import java.util.List;

/**
 * Created by Ahmed Orabi on 7/21/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private List<Result> movieList;
    private MovieCallback movieCallback;


    public MovieAdapter(Context context, List<Result> data, MovieCallback callback) {
        this.movieList = data;
        this.movieCallback = callback;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        GridViewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.grid_view_item, parent, false);

        binding.setCallBack(movieCallback);

        return new MyViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.setMovieItem(movieList.get(position));
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        final GridViewItemBinding binding;

        MyViewHolder(GridViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
