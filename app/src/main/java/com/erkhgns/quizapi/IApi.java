package com.erkhgns.quizapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApi {

    @GET("api.php?")
    Call<SendResponse> getQuestion(@Query("amount") int amount, @Query("type") String type);
}
