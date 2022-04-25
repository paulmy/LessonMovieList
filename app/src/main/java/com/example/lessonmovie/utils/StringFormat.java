package com.example.lessonmovie.utils;

import android.content.res.Resources;

import com.example.lessonmovie.R;

public class StringFormat {
    public static String formatTime(Resources res, int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        if (h > 0 && m > 0)
            return String.format(res.getString(R.string.time_h) + " " + res.getString(R.string.time_min), h, m);
        else if (h > 0) return String.format(res.getString(R.string.time_h), h);
        else return String.format(res.getString(R.string.time_min), m);

    }

    public static String formatMoney(Resources res, int money) {
        int m = money / 1000000;
        int k = (money - m*1000000)/1000;
        if (m > 0 && k>0)
            return String.format(res.getString(R.string.money_m) + " " +res.getString(R.string.money_k), m,k);
        else
            return String.format(res.getString(R.string.money_k), k);
    }
}
