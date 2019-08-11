package com.test.ahmedorabi.movieapp.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.KnownItemBinding;
import com.test.ahmedorabi.movieapp.repository.data.knownResponse.Cast;
import com.test.ahmedorabi.movieapp.view.callback.KnownCallback;

import java.util.List;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.MyViewHolder> {


    private List<Cast> movieList;
    private KnownCallback mCallback;


    public GridViewAdapter(Context context, List<Cast> data, KnownCallback callback) {
        this.movieList = data;
        this.mCallback = callback;
    }

    @NonNull
    @Override
    public GridViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        KnownItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.known_item, parent, false);

        binding.setCallback(mCallback);
        return new MyViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull GridViewAdapter.MyViewHolder holder, int position) {

        holder.binding.setCastItem(movieList.get(position));
        holder.binding.executePendingBindings();

        Cast movie = movieList.get(position);

        String title = movie.getTitle();

        String name = movie.getmName();

        if (!TextUtils.isEmpty(name)) {
            holder.binding.titleItem.setText(name);
        } else {
            holder.binding.titleItem.setText(title);

        }



    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        final KnownItemBinding binding;

        MyViewHolder(KnownItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }


}
