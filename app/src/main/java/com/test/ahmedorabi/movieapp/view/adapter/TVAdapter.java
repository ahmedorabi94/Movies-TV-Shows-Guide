package com.test.ahmedorabi.movieapp.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.TvshowItemBinding;
import com.test.ahmedorabi.movieapp.model.appModels.tvModel.Result;
import com.test.ahmedorabi.movieapp.view.callback.TVCallBack;

import java.util.List;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.MyViewHolder> {


    private List<Result> tvList;
    private TVCallBack callBack;


    public TVAdapter(Context context, List<Result> data, TVCallBack tvCallBack) {
        this.tvList = data;
        this.callBack = tvCallBack;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        TvshowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.tvshow_item, parent, false);

        binding.setCallBack(callBack);
        return new MyViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.setTVItem(tvList.get(position));
        holder.binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        final TvshowItemBinding binding;

        MyViewHolder(TvshowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
