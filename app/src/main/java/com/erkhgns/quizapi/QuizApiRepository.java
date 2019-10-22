package com.erkhgns.quizapi;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiRepository {

    private Retrofit retrofit;

    private IApi api;


    public QuizApiRepository (Context context){
        retrofit = getInstanceOfRetrofit();
        api = getInstanceOfApi();

    }

    public Retrofit getInstanceOfRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private IApi getInstanceOfApi() {
        return retrofit.create(IApi.class);
    }

     void getTrueOrFalseQuestion(final MutableLiveData<List<SendResponse.RData>> mutableLiveData , final MutableLiveData<String> currentQuestion) {
        Call<SendResponse> responseCall = api.getQuestion(20, "boolean");

        responseCall.enqueue(new Callback<SendResponse>() {
            @Override
            public void onResponse(Call<SendResponse> call, Response<SendResponse> response) {
                if (!response.isSuccessful()) {
                    Log.e("error", response.message());
                    return;
                }

                SendResponse question = response.body();

                if (question != null) {
                    if (question.getResponse_code() == 0) {
                        mutableLiveData.postValue(question.getData());
                        currentQuestion.postValue(question.getData().get(0).getQuestion());
                    }
                }


            }

            @Override
            public void onFailure(Call<SendResponse> call, Throwable t) {

            }
        });
    }

}
