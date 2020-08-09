package com.example.swipablecards.retrofit;

import com.example.swipablecards.retrofit.interceptor.CustomInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Lokesh chennamchetty
 * @date 08/08/2020
 */

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://gist.githubusercontent.com";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getHTTPClientForTokenRequest())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getHTTPClientForTokenRequest() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(new CustomInterceptor());
        return httpClientBuilder.build();
    }


}

