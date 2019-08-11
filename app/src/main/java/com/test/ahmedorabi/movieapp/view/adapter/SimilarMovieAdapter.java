package com.test.ahmedorabi.movieapp.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.SimilarMovieItemBinding;
import com.test.ahmedorabi.movieapp.repository.data.similar.Result;
import com.test.ahmedorabi.movieapp.view.callback.SimilarMovieCallback;

import java.util.List;

public class SimilarMovieAdapter extends RecyclerView.Adapter<SimilarMovieAdapter.MyViewHolder> {


    private List<Result> similarList;
    private SimilarMovieCallback movieCallback;


    public SimilarMovieAdapter(Context context, List<Result> data, SimilarMovieCallback callback) {
        this.similarList = data;
        this.movieCallback = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        SimilarMovieItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.similar_movie_item, parent, false);

        binding.setCallBack(movieCallback);

        return new MyViewHolder(binding);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.setMovieItem(similarList.get(position));
        holder.binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return similarList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        final SimilarMovieItemBinding binding;

        MyViewHolder(SimilarMovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
