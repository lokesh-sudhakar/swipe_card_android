package com.example.swipablecards.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Lokesh chennamchetty
 * @date 08/08/2020
 */
public class CardsResponse {

    private Throwable error;

    @SerializedName("data")
    private List<CardMetaData> data = null;

    public CardsResponse(List<CardMetaData> data) {
        this.data = data;
    }

    public CardsResponse(Throwable error) {
        this.error = error;
    }


    public List<CardMetaData> getData() {
        return data;
    }
}