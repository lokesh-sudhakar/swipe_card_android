package com.example.swipablecards.repository;

import com.example.swipablecards.model.CardsResponse;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author Lokesh chennamchetty
 * @date 08/08/2020
 */


public class SwipeCardRepository {

    private Retrofit mRetrofit;


    public SwipeCardRepository(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }

    public Observable<CardsResponse> getCardsData() {
        return  mRetrofit.create(SwipeCardAPI.class).getCardsData();
    }
}
