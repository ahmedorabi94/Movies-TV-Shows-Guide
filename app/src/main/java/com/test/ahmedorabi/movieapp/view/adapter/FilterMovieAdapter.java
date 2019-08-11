package com.test.ahmedorabi.movieapp.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.SearchRowItemBinding;
import com.test.ahmedorabi.movieapp.model.appModels.searchModel.Result;
import com.test.ahmedorabi.movieapp.view.callback.SearchCallback;

import java.util.List;

public class FilterMovieAdapter extends RecyclerView.Adapter<FilterMovieAdapter.MyViewHolder> {


    private Context context;
    private List<Result> resultList;
    private SearchCallback mCallback;


    public FilterMovieAdapter(Context context, List<Result> list, SearchCallback callback) {
        this.context = context;
        this.resultList = list;
        this.mCallback = callback;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SearchRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.search_row_item, parent, false);

        binding.setCallback(mCallback);
        return new MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.setMovieItem(resultList.get(position));
        holder.binding.executePendingBindings();


        Result item = resultList.get(position);

        if (item.getMediaType().equals("movie")) {
            holder.binding.titleRow.setText(item.getTitle());
        } else if (item.getMediaType().equals("tv")) {
            holder.binding.titleRow.setText(item.getName());
        }

        String overview = item.getOverview();
        if (!TextUtils.isEmpty(overview)) {
            holder.binding.overviewRow.setText(overview);

        } else {
            holder.binding.overviewRow.setText("N/A");

        }


        String url = item.getPosterPath();
        if (!TextUtils.isEmpty(url)) {
            String baseUrl = "http://image.tmdb.org/t/p/w200";
            String finalUrl = baseUrl + url;
            Glide.with(context)
                    .load(finalUrl)
                    .into(holder.binding.imageRowItem);
        }


    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        final SearchRowItemBinding binding;

        MyViewHolder(SearchRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }


    }


}
