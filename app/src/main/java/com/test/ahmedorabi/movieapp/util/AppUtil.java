package com.test.ahmedorabi.movieapp.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppUtil {


    public static String formatDate(String datestr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date date = fmt.parse(datestr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d, yyyy", Locale.US);
            return fmtOut.format(date);
        } catch (ParseException ignored) {

        }

        return "";
    }


    public static float ScreanWidth(Activity activity) {

        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        float density = activity.getResources().getDisplayMetrics().density;
        return displayMetrics.widthPixels / density;
    }


}
