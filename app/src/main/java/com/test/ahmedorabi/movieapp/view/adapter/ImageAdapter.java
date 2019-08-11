package com.test.ahmedorabi.movieapp.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.ImagesitemBinding;
import com.test.ahmedorabi.movieapp.model.appModels.imagesResponse.Still;
import com.test.ahmedorabi.movieapp.view.callback.EpisodeImageCallback;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private List<Still> List;
    private EpisodeImageCallback mCallback;

    public ImageAdapter(Context context, List<Still> data, EpisodeImageCallback callback) {
        this.List = data;
        this.mCallback = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ImagesitemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.imagesitem, parent, false);


        binding.setCallback(mCallback);
        return new MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.setImageItem(List.get(position));
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        final ImagesitemBinding binding;

        MyViewHolder(ImagesitemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }
}
