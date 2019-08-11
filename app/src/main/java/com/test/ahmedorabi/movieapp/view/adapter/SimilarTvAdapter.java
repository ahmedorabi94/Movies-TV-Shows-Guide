package com.test.ahmedorabi.movieapp.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.SimilarTvshowItemBinding;
import com.test.ahmedorabi.movieapp.repository.data.similarTvModel.Result;
import com.test.ahmedorabi.movieapp.view.callback.SimilarTVCallback;

import java.util.List;


public class SimilarTvAdapter extends RecyclerView.Adapter<SimilarTvAdapter.MyViewHolder> {

    private List<Result> similarList;
    private SimilarTVCallback mCallback;


    public SimilarTvAdapter(Context context, List<Result> data, SimilarTVCallback callback) {
        this.similarList = data;
        this.mCallback = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SimilarTvshowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.similar_tvshow_item, parent, false);

        binding.setCallBack(mCallback);
        return new MyViewHolder(binding);

    }


    @Override
    public void onBindViewHolder(@NonNull SimilarTvAdapter.MyViewHolder holder, int position) {


        holder.binding.setItem(similarList.get(position));
        holder.binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return similarList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        final SimilarTvshowItemBinding binding;

        MyViewHolder(SimilarTvshowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }


}


