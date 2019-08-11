package com.test.ahmedorabi.movieapp.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.RecyclerViewItemBinding;
import com.test.ahmedorabi.movieapp.repository.data.creditsModel.Cast;
import com.test.ahmedorabi.movieapp.view.callback.CastCallback;

import java.util.List;

/**
 * Created by Ahmed Orabi on 11/05/2018.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {

    private List<Cast> castList;
    private CastCallback mCallback;


    public MyRecyclerViewAdapter(List<Cast> actorImages, Context context, CastCallback callback) {
        this.castList = actorImages;
        this.mCallback = callback;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerViewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_view_item, parent, false);

        binding.setCallBack(mCallback);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.binding.setCastItem(castList.get(position));
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return (null != castList ? castList.size() : 0);
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        final RecyclerViewItemBinding binding;

        CustomViewHolder(RecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }


}
