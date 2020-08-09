package com.example.swipablecards.adapter;

import android.util.Log;
import android.widget.TextView;

import com.example.swipablecards.R;
import com.example.swipablecards.model.CardMetaData;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

/**
 * @author Lokesh chennamchetty
 * @date 09/08/2020
 */
@Layout(R.layout.card_layout)
public class CardViewAdapter {


    @View(R.id.description)
    public TextView description;

    private CardMetaData cardData;

    public CardViewAdapter( CardMetaData cardMetaData) {
        cardData = cardMetaData;
    }

    @Resolve
    public void onResolved(){
        description.setText(cardData.getDescription());
    }

    @SwipeOut
    public void onSwipedOut(){
    }

    @SwipeCancelState
    public void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    public void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");
    }


    @SwipeInState
    public void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    public void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }
}
