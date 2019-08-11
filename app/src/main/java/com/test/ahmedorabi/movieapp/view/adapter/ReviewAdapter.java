package com.test.ahmedorabi.movieapp.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.ReviewListItemBinding;
import com.test.ahmedorabi.movieapp.model.appModels.ReveiwItem;

import java.util.ArrayList;

/**
 * Created by Ahmed Orabi on 15/05/2018.
 */

public class ReviewAdapter extends ArrayAdapter<ReveiwItem> {

    private ArrayList<ReveiwItem> data;

    private boolean isTextViewClicked = false;


    public ReviewAdapter(Context context, ArrayList<ReveiwItem> items) {
        super(context, 0, items);
        data = items;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {


        @SuppressLint("ViewHolder") final ReviewListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.review_list_item, parent, false);

        binding.setReviewItem(data.get(position));


        binding.expandCollapse.setOnClickListener(v -> {
            if (isTextViewClicked) {
                binding.contentReview.setMaxLines(8);
                binding.expandCollapse.setImageResource(R.drawable.ic_chevron_down_white_24dp);
                isTextViewClicked = false;
            } else {
                binding.contentReview.setMaxLines(Integer.MAX_VALUE);
                binding.expandCollapse.setImageResource(R.drawable.ic_chevron_up_white_24dp);
                isTextViewClicked = true;
            }
        });

        return binding.getRoot();


    }
}
