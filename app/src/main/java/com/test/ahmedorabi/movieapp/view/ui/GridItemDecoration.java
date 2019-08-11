package com.test.ahmedorabi.movieapp.view.ui;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ahmed Orabi on 7/22/2018.
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private final int mHorizontalSpacing;
    private final int mVerticalSpaceHeight;


    GridItemDecoration(int horizontalSpacing) {
        this.mHorizontalSpacing = horizontalSpacing;
        this.mVerticalSpaceHeight = 0;
    }

    GridItemDecoration(int horizontalSpacing, int verticalSpaceHeight) {
        this.mVerticalSpaceHeight = verticalSpaceHeight;
        this.mHorizontalSpacing = horizontalSpacing;

    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        if (mVerticalSpaceHeight == 0) {
            outRect.left = mHorizontalSpacing;
        } else {
            outRect.left = mHorizontalSpacing;
            outRect.bottom = mVerticalSpaceHeight;
        }


    }


}
