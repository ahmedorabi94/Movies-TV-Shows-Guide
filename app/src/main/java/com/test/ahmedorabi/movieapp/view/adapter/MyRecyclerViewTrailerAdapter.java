package com.test.ahmedorabi.movieapp.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.ahmedorabi.movieapp.repository.data.trailermodel.TrailerResult;
import com.bumptech.glide.Glide;
import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.RecyclerViewItemTrailerBinding;
import com.test.ahmedorabi.movieapp.view.callback.TrailerCallBack;

import java.util.List;

/**
 * Created by Ahmed Orabi on 15/05/2018.
 */

public class MyRecyclerViewTrailerAdapter extends RecyclerView.Adapter<MyRecyclerViewTrailerAdapter.CustomViewHolder> {
    private Context context;
    private TrailerCallBack mCallback;
    private List<TrailerResult> trailerResults;



    public MyRecyclerViewTrailerAdapter(List<TrailerResult> trailerResults, Context context, TrailerCallBack callBack) {
        this.trailerResults = trailerResults;
        this.context = context;
        this.mCallback = callBack;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        RecyclerViewItemTrailerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.recycler_view_item_trailer, viewGroup, false);

        binding.setMCallback(mCallback);

        return new CustomViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int position) {

        viewHolder.binding.setKey(trailerResults.get(position).getKey());

        viewHolder.binding.executePendingBindings();

        viewHolder.binding.tvType.setText(trailerResults.get(position).getType());


        String key = trailerResults.get(position).getKey();

        if (!TextUtils.isEmpty(key)) {
            String url = "https://img.youtube.com/vi/" + key + "/0.jpg";
            Glide.with(context).load(url).into(viewHolder.binding.thumbImage);

        }



    }

    @Override
    public int getItemCount() {
        return (null != trailerResults ? trailerResults.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {


        final RecyclerViewItemTrailerBinding binding;

        CustomViewHolder(RecyclerViewItemTrailerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
