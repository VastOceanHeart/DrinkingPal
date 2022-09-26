package com.example.drinkingpal.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleImageRetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.googleapis.com/";

    public static GoogleImageRetrofitInterface getRetrofitService() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(GoogleImageRetrofitInterface.class);
    }
}
