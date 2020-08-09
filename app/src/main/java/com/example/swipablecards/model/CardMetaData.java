package com.example.swipablecards.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Lokesh chennamchetty
 * @date 08/08/2020
 */

public class CardMetaData {

    @Expose
    @SerializedName("id")
    private String id;


    @Expose
    @SerializedName("text")
    private String description;

    public CardMetaData(String id, String description){
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }
}
