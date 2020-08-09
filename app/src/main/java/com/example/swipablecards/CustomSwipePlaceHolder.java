package com.example.swipablecards;

import android.content.Context;

import com.mindorks.placeholderview.SwipePlaceHolderView;

/**
 * @author Lokesh chennamchetty
 * @date 09/08/2020
 */
public class CustomSwipePlaceHolder extends SwipePlaceHolderView {


    public CustomSwipePlaceHolder(Context context) {
        super(context);
    }

    @Override
    protected <T> void addView(T resolver, int position) {
        super.addView(resolver, position);
    }
}

