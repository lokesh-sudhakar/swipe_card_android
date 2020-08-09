package com.example.swipablecards.retrofit.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Lokesh chennamchetty
 * @date 08/08/2020
 */

public class CustomInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.isSuccessful() && response.body()!= null) {
            String responseBody = response.body().string().replaceFirst("/","");
            Response.Builder newResponse = response.newBuilder();
            newResponse.body(ResponseBody.create(MediaType.parse("application/json"), responseBody));
            response = newResponse.build();
        }
        return response;
    }
}