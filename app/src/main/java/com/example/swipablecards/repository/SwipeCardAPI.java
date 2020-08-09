package com.example.swipablecards.repository;

import com.example.swipablecards.model.CardsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author Lokesh chennamchetty
 * @date 08/08/2020
 */
public interface SwipeCardAPI {

    @GET(ApiUrls.CARD_DATA_API)
    Observable<CardsResponse> getCardsData();
}
