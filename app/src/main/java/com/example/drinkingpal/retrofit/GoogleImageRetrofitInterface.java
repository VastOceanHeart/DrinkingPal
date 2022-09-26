package com.example.drinkingpal.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleImageRetrofitInterface {

    @GET("customsearch/v1")
    Call<SearchResponse> customSearch(@Query("key") String API_KEY,
                                      @Query("cx") String SEARCH_ID_cx,
                                      @Query("q") String keyword,
                                      @Query("searchType") String searchType,
                                      @Query("num") int num,
                                      @Query("start") int start);
}
