package com.test.ahmedorabi.movieapp.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.FavItemBinding;
import com.test.ahmedorabi.movieapp.model.dbroom.Movie;
import com.test.ahmedorabi.movieapp.view.callback.FavItemCallBack;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MyViewHolder> {


    private List<Movie> movieList;
    private FavItemCallBack mCallback;

    public FavAdapter(Context context, List<Movie> data, FavItemCallBack callBack) {
        this.movieList = data;
        this.mCallback = callBack;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        FavItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fav_item, parent, false);

        binding.setCallback(mCallback);
        return new MyViewHolder(binding);


    }


    @Override
    public void onBindViewHolder(@NonNull FavAdapter.MyViewHolder holder, int position) {

        holder.binding.setFavItem(movieList.get(position));
        holder.binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        final FavItemBinding binding;

        MyViewHolder(FavItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
